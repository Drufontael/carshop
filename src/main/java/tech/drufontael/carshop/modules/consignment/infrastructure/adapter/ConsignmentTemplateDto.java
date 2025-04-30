package tech.drufontael.carshop.modules.consignment.infrastructure.adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.shared.utils.PorExtenso;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConsignmentTemplateDto {

    private String consignorName;
    private String consignorAddress;
    private String consignorRegister;
    private String consignorIdentity;
    private String consignorPhone;
    private String vehicleBrand;
    private String vehicleModel;
    private String vehiclePlate;
    private String vehicleYears;
    private String vehicleMileage;
    private String vehicleChassi;
    private String vehicleRenavam;
    private String vehicleOwner;
    private String vehicleOwnerRegister;
    private String vehiclePrice;
    private String vehiclePriceInWords;
    private String vehicleDocumentYear;
    private String commission;
    private boolean hasVehicleManual;
    private boolean hasVehicleExtraKeys;
    private boolean hasVehicleTools;
    private boolean hasVehicleDut;
    private String consignmentEntryTime;
    private String consignmentEntryDate;
    private String consignmentLocale;

    public static ConsignmentTemplateDto dtoFactory(Consignment consignment){
        DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm");
        String address=consignment.getConsignor().getAddress().getCity()+", "
                +consignment.getConsignor().getAddress().getState();
        String year=consignment.getVehicle().getVehicleData().getManufactureYear().getValue()+"/"+
                consignment.getVehicle().getVehicleData().getVehicleModel().getModelYear().getValue();
        String price=String.format("%.2f",consignment.getVehicle().getPrice().floatValue());
        String priceInWords= PorExtenso.converter(consignment.getVehicle().getPrice().intValue());
        String date=consignment.getDateTime().format(dateFormatter);
        String time=consignment.getDateTime().format(timeFormatter);
        String location=consignment.getAddress().getCity()+", "
                +consignment.getAddress().getState();

        return new ConsignmentTemplateDtoBuilder()
                .commission("4")
                .consignorName(consignment.getConsignor().getName())
                .consignorAddress(address)
                .consignorRegister(consignment.getConsignor().getRegister().getValue())
                .consignorIdentity("--")
                .consignorPhone(consignment.getConsignor().getContact().getCellPhone().getValue())
                .vehicleBrand(consignment.getVehicle().getVehicleData().getVehicleModel().getBrand().getName())
                .vehicleModel(consignment.getVehicle().getVehicleData().getVehicleModel().getModelo())
                .vehiclePlate(consignment.getVehicle().getVehicleData().getPlate().getValue())
                .vehicleYears(year)
                .vehicleMileage(consignment.getVehicle().getMileage()+"")
                .vehicleChassi(consignment.getVehicle().getVehicleData().getChassi().getValue())
                .vehicleRenavam(consignment.getVehicle().getVehicleData().getRenavam().getValue())
                .vehicleOwner(consignment.getVehicle().getOwner().getName())
                .vehicleOwnerRegister(consignment.getVehicle().getOwner().getRegister().getValue())
                .vehiclePrice(price)
                .vehiclePriceInWords(priceInWords)
                .vehicleDocumentYear("--")
                .hasVehicleManual(consignment.getVehicle().getAdditionalInformations().contains("manual"))
                .hasVehicleDut(consignment.getVehicle().getAdditionalInformations().contains("dut"))
                .hasVehicleTools(consignment.getVehicle().getAdditionalInformations().contains("tools"))
                .hasVehicleExtraKeys(consignment.getVehicle().getAdditionalInformations().contains("extraKeys"))
                .consignmentEntryDate(date)
                .consignmentEntryTime(time)
                .consignmentLocale(location)
                .build();
    }


}
