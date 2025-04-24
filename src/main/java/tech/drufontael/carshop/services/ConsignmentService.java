package tech.drufontael.carshop.services;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.adapter.PdfContractAdapter;
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
    @Autowired
    private PdfContractAdapter pdfAdapter;
    
    

    public ConsignmentDto create(ConsignmentDto consignment){
        var consignor=consignorService.read(consignment.getConsignorId());
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
        if(source.getConsignorId()!=null) target.setConsignor(consignorService.read(source.getConsignorId())
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

    public ConsignmentDto findByVehiclePlate(String plate){
        vehicleService.read(plate);
        return new ConsignmentDto(repository.findByVehiclePlate(plate));
    }

    public byte[] createPdfContract(UUID id){
        var consignment=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        consignment.getVehicle().setPriceInWords(PorExtenso.converter(consignment.getVehicle().getPrice()));
        consignment.setFormattedDate(consignment.getDate().format(formatter));

        return pdfAdapter.documentCreator(consignment);
    }


}
