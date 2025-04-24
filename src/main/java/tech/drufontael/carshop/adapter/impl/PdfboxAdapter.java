package tech.drufontael.carshop.adapter.impl;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import tech.drufontael.carshop.adapter.PdfContractAdapter;
import tech.drufontael.carshop.model.Consignment;
import tech.drufontael.carshop.utils.PorExtenso;
import tech.drufontael.carshop.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PdfboxAdapter implements PdfContractAdapter {

    private final String PATH="C:\\Geral";

    @Override
    public byte[] documentCreator(Consignment consignment) {
        try {
            File file=new File("src/main/resources/static/CONTRATO.pdf");
            PDDocument pDDocument = Loader.loadPDF(file);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("nomeConsignante");
            field.setValue(consignment.getConsignor().getName());
            field = pDAcroForm.getField("endereco");
            field.setValue(consignment.getConsignor().getAddress());
            field = pDAcroForm.getField("cpfCnpj");
            field.setValue(Utils.registerFormat(consignment.getConsignor().getRegister()));
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
            field.setValue(consignment.getLocation() +" "+ consignment.getFormattedDate());


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            pDDocument.save(baos);
            pDDocument.save(PATH+"/CONTRATO"+consignment.getVehicle().getPlate()+".pdf");
            pDDocument.close();
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
