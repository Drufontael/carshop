package tech.drufontael.carshop.modules.consignment.infrastructure;

import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public interface ConsignmentManager {
    Consignment createConsignment(Long consignorId, Long vehicleId,String commission,String minimumPrice, Address address);
    Consignment getConsignmentById(Long id);
    List<Consignment> getConsignmentByConsignorAndVehicle(Long consignorId, Long vehicleId);
    Consignment updateConsignment(Long id, BigDecimal minimumPrice,double commission,Address address);
    void deleteConsignment(Long consignmentId);
    byte[] generateAgreement(Long consignmentId);
}
