package tech.drufontael.carshop.modules.vehicle.api.dto.requets;

public record VehicleRequest(
        Long modelId,
        String plate,
        String chassi,
        String renavan,
        Integer manufactureYear,
        String color
) {
}
