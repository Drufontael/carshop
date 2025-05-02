package tech.drufontael.carshop.modules.vehicle.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VehicleModelResponse {
    private String brand;
    private String model;
    private Integer year;

    public static VehicleModelResponse fromDomain(VehicleModel vehicleModel){
        return VehicleModelResponse.builder()
                .brand(vehicleModel.getBrand().getName())
                .model(vehicleModel.getModel())
                .year(vehicleModel.getModelYear().getValue())
                .build();
    }
}
