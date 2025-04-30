package tech.drufontael.carshop.modules.vehicle.api.dto.requets;

import java.math.BigDecimal;

public record VariableDataRequest(
        Long ownerId,
        BigDecimal price,
        Integer mileage
) {
}
