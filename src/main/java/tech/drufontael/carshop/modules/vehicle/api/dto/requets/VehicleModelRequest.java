package tech.drufontael.carshop.modules.vehicle.api.dto.requets;

public record VehicleModelRequest(
        Long brandId,
        String model,
        Integer modelYear
) {
}
