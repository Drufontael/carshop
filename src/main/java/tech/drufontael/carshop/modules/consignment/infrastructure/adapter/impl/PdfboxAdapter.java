package tech.drufontael.carshop.modules.consignment.infrastructure.adapter.impl;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.consignment.infrastructure.adapter.ConsignmentTemplateDto;
import tech.drufontael.carshop.modules.consignment.infrastructure.adapter.PdfContractAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PdfboxAdapter implements PdfContractAdapter {

    private final String PATH="C:\\Geral";

    @Override
    public byte[] documentCreator(Consignment consignment) {
        ConsignmentTemplateDto templateDto=ConsignmentTemplateDto.dtoFactory(consignment);
        try {
            File file=new File("src/main/resources/static/CONTRATO.pdf");
            PDDocument pDDocument = Loader.loadPDF(file);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("nomeConsignante");
            field.setValue(templateDto.getConsignorName());
            field = pDAcroForm.getField("endereco");
            field.setValue(templateDto.getConsignorAddress());
            field = pDAcroForm.getField("cpfCnpj");
            field.setValue(templateDto.getConsignorRegister());
            field = pDAcroForm.getField("rg");
            field.setValue(templateDto.getConsignorIdentity());
            field = pDAcroForm.getField("telefone");
            field.setValue(templateDto.getConsignorPhone());
            field = pDAcroForm.getField("marca");
            field.setValue(templateDto.getVehicleBrand());
            field = pDAcroForm.getField("modelo");
            field.setValue(templateDto.getVehicleModel());
            field = pDAcroForm.getField("anoMod");
            field.setValue(templateDto.getVehicleYears());
            field = pDAcroForm.getField("km");
            field.setValue(templateDto.getVehicleMileage());
            field = pDAcroForm.getField("placa");
            field.setValue(templateDto.getVehiclePlate());
            field = pDAcroForm.getField("chassi");
            field.setValue(templateDto.getVehicleChassi());
            field = pDAcroForm.getField("renavam");
            field.setValue(templateDto.getVehicleRenavam());
            field = pDAcroForm.getField("nomeProprietario");
            field.setValue(templateDto.getVehicleOwner());
            field = pDAcroForm.getField("cpfCnpjProp");
            field.setValue(templateDto.getVehicleOwnerRegister());
            field = pDAcroForm.getField("preco");
            field.setValue("R$ "+templateDto.getVehiclePrice()+" ("+
                    templateDto.getVehiclePriceInWords()+")");
            field = pDAcroForm.getField("manual");
            field.setValue(templateDto.isHasVehicleManual()?"X":" ");
            field = pDAcroForm.getField("chaveReserva");
            field.setValue(templateDto.isHasVehicleExtraKeys()?"X":" ");
            field = pDAcroForm.getField("ferramentas");
            field.setValue(templateDto.isHasVehicleTools()?"X":" ");
            field = pDAcroForm.getField("dut");
            field.setValue(templateDto.isHasVehicleDut()?"X":" ");
            field = pDAcroForm.getField("docRodar");
            field.setValue(templateDto.getVehicleDocumentYear());
            field = pDAcroForm.getField("horaEntrada");
            field.setValue(templateDto.getConsignmentEntryTime());
            field = pDAcroForm.getField("localData");
            field.setValue(templateDto.getConsignmentLocale() +" "+ templateDto.getConsignmentEntryDate());


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            pDDocument.save(baos);
            pDDocument.save(PATH+"/CONTRATO"+templateDto.getVehiclePlate()+".pdf");
            pDDocument.close();
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
