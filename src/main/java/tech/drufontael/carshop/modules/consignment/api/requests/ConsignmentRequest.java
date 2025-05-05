package tech.drufontael.carshop.modules.consignment.api.requests;

import tech.drufontael.carshop.modules.shared.Address;

public record ConsignmentRequest(Long consignorId, Long vehicleId, String commission, String minimumPrice, Address address) {

}
