package tech.drufontael.carshop.modules.customer.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ConsignmentResponse {
    private String consignorName;
    private String consignorRegister;
    private String vehicleBrandAndModel;
    private String vehiclePlate;
    private BigDecimal minimumPrice;
    private Double commission;
    private LocalTime entryTime;
    private LocalDate entryDate;
    private String location;

    public static ConsignmentResponse fromDomain(Consignment consignment){
        return ConsignmentResponse.builder()
                .consignorName(consignment.getConsignor().getName())
                .consignorRegister(consignment.getConsignor().getRegister().getValue())
                .vehicleBrandAndModel(consignment.getVehicle().getVehicleData().getVehicleModel().getBrand().getName()+
                        " "+consignment.getVehicle().getVehicleData().getVehicleModel().getModel())
                .vehiclePlate(consignment.getVehicle().getVehicleData().getPlate().getValue())
                .minimumPrice(consignment.getMinimumPrice())
                .commission(consignment.getCommission())
                .entryTime(consignment.getDateTime().toLocalTime())
                .entryDate(consignment.getDateTime().toLocalDate())
                .location(consignment.getAddress().getCity()+", "+consignment.getAddress().getState())
                .build();
    }
}
