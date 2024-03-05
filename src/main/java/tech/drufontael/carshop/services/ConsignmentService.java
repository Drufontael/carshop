package tech.drufontael.carshop.services;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.dto.ConsignmentDto;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Consignment;
import tech.drufontael.carshop.repositories.ConsignmentRepository;
import tech.drufontael.carshop.utils.PorExtenso;
import tech.drufontael.carshop.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConsignmentService {
    @Autowired
    private ConsignmentRepository repository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ConsignorService consignorService;
    
    

    public ConsignmentDto create(ConsignmentDto consignment){
        var consignor=consignorService.read(consignment.getConsignorRegister());
        var vehicle=vehicleService.read(consignment.getVehiclePlate());
        var newConsignment = consignment.toConsignment();
        if(newConsignment.getDate()==null) newConsignment.setDate(LocalDate.now());
        if(newConsignment.getEntryTime()==null) newConsignment.setEntryTime(LocalTime.now());
        newConsignment.setConsignor(consignor.toConsignor());
        newConsignment.setVehicle(vehicle.toVehicle());
        return new ConsignmentDto(repository.save(newConsignment));
    }

    public ConsignmentDto read(UUID id){
        return new ConsignmentDto(repository.findById(id)
                .orElseThrow(()->new ResourseNotFoundException("Consignment not found")));
    }

    public ConsignmentDto update(UUID id, ConsignmentDto source){
        var target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found."));
        Utils.copyNonNullProperties(source,target);
        if(source.getConsignorRegister()!=null) target.setConsignor(consignorService.read(source.getConsignorRegister())
                .toConsignor());
        if(source.getVehiclePlate()!=null) target.setVehicle(vehicleService.read(source.getVehiclePlate())
                .toVehicle());
        return new ConsignmentDto(repository.save(target));
    }

    public void delete(UUID id){
        Consignment consignment=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found"));
        repository.delete(consignment);
    }

    public List<ConsignmentDto> findAll(){
        List<ConsignmentDto> dtos=new ArrayList<>();
        for(Consignment consignment:repository.findAll()){
            dtos.add(new ConsignmentDto(consignment));
        }
        return dtos;
    }

    public List<ConsignmentDto> findByConsignorRegister(String register){
        consignorService.read(register);
        List<ConsignmentDto> list=new ArrayList<>();
        for(Consignment consignment:repository.findByConsignorRegister(register)){
            list.add(new ConsignmentDto(consignment));
        }
        return list;
    }

    public ConsignmentDto findByVehiclePlate(String plate){
        vehicleService.read(plate);
        return new ConsignmentDto(repository.findByVehiclePlate(plate));
    }

    public byte[] createPdfContract(UUID id, String path){
        var consignment=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            File file=new File("src/main/resources/static/CONTRATO.pdf");
            PDDocument pDDocument = Loader.loadPDF(file);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("nomeConsignante");
            field.setValue(consignment.getConsignor().getName());
            field = pDAcroForm.getField("endereco");
            field.setValue(consignment.getConsignor().getAddress());
            field = pDAcroForm.getField("cpfCnpj");
            field.setValue(consignment.getConsignor().getRegister());
            field = pDAcroForm.getField("rg");
            field.setValue(consignment.getConsignor().getIdentity());
            field = pDAcroForm.getField("telefone");
            field.setValue(consignment.getConsignor().getPhone());
            field = pDAcroForm.getField("marca");
            field.setValue(consignment.getVehicle().getBrand());
            field = pDAcroForm.getField("modelo");
            field.setValue(consignment.getVehicle().getModelCar());
            field = pDAcroForm.getField("anoMod");
            field.setValue(consignment.getVehicle().getYear()+"/"
                    +consignment.getVehicle().getModelYear());
            field = pDAcroForm.getField("km");
            field.setValue(""+consignment.getVehicle().getKm());
            field = pDAcroForm.getField("placa");
            field.setValue(consignment.getVehicle().getPlate());
            field = pDAcroForm.getField("chassi");
            field.setValue(consignment.getVehicle().getChassis());
            field = pDAcroForm.getField("renavam");
            field.setValue(consignment.getVehicle().getRenavan());
            field = pDAcroForm.getField("nomeProprietario");
            field.setValue(consignment.getVehicle().getOwnerName());
            field = pDAcroForm.getField("cpfCnpjProp");
            field.setValue(consignment.getVehicle().getOwnerIdentity());
            field = pDAcroForm.getField("preco");
            field.setValue("R$ "+consignment.getVehicle().getPrice()+" ("+
                    PorExtenso.converter(consignment.getVehicle().getPrice())+")");
            field = pDAcroForm.getField("manual");
            field.setValue(consignment.getVehicle().getManual()?"X":" ");
            field = pDAcroForm.getField("chaveReserva");
            field.setValue(consignment.getVehicle().getExtraKey()?"X":" ");
            field = pDAcroForm.getField("ferramentas");
            field.setValue(consignment.getVehicle().getTools()?"X":" ");
            field = pDAcroForm.getField("dut");
            field.setValue(consignment.getVehicle().getDut()?"X":" ");
            field = pDAcroForm.getField("docRodar");
            field.setValue(consignment.getVehicle().getYearDocument()+"");
            field = pDAcroForm.getField("horaEntrada");
            field.setValue(consignment.getEntryTime().toString());
            field = pDAcroForm.getField("localData");
            field.setValue(consignment.getLocation() +" "+ consignment.getDate().format(formatter));


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            pDDocument.save(baos);
            pDDocument.save(path+"/CONTRATO"+consignment.getVehicle().getPlate()+".pdf");
            pDDocument.close();
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
