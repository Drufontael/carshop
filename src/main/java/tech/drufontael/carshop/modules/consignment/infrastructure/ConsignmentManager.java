package tech.drufontael.carshop.modules.consignment.infrastructure;

import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.shared.Address;

import java.util.List;

public interface ConsignmentManager {
    Consignment createConsignment(Long consignorId, Long vehicleId, Address address);
    Consignment getConsignmentById(Long id);
    List<Consignment> getAllConsignment();
    Consignment updateConsignment(Long id,Consignment consignment);
    void deleteConsignment(Long consignmentId);
    byte[] generateAgreement(Long consignmentId);
}
