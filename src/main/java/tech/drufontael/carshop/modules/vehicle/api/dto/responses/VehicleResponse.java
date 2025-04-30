package tech.drufontael.carshop.modules.vehicle.api.dto.responses;

import lombok.*;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VehicleResponse {
    private Long id;
    private String brand;
    private String model;
    private Integer modelYear;
    private String plate;
    private String chassi;
    private String renavan;
    private Integer manufactureYear;
    private String color;

    public static VehicleResponse fromDomain(Vehicle vehicle){
        return new VehicleResponseBuilder()
                .id(vehicle.getId())
                .brand(vehicle.getVehicleData().getVehicleModel().getBrand().getName())
                .model(vehicle.getVehicleData().getVehicleModel().getModelo())
                .modelYear(vehicle.getVehicleData().getVehicleModel().getModelYear().getValue())
                .plate(vehicle.getVehicleData().getPlate().getValue())
                .chassi(vehicle.getVehicleData().getChassi().getValue())
                .renavan(vehicle.getVehicleData().getRenavam().getValue())
                .manufactureYear(vehicle.getVehicleData().getManufactureYear().getValue())
                .color(vehicle.getVehicleData().getColor().getCor())
                .build();
    }
}
