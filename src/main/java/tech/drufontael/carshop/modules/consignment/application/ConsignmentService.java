package tech.drufontael.carshop.modules.consignment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.drufontael.carshop.exceptions.ResourceNotFoundException;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.consignment.infrastructure.ConsignmentManager;
import tech.drufontael.carshop.modules.consignment.infrastructure.ConsignmentRepository;
import tech.drufontael.carshop.modules.consignment.infrastructure.adapter.PdfContractAdapter;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerManager;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.shared.CarshopConstants;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsignmentService implements ConsignmentManager {

    private final ConsignmentRepository repository;
    private final VehicleManager vehicleManager;
    private final CustomerManager customerManager;
    private final PdfContractAdapter contractAdapter;

    @Override
    @Transactional
    public Consignment createConsignment(Long consignorId, Long vehicleId, Address address) {
        Customer consignor=customerManager.getById(consignorId);
        Vehicle vehicle=vehicleManager.getVehicleById(vehicleId);
        LocalDateTime entry=LocalDateTime.now();
        if(address==null){
            address= CarshopConstants.SHOP_ADDRESS;
        }
        return repository.save(new Consignment(
                null,
                consignor,
                vehicle,
                address,
                vehicle.getPrice(),
                CarshopConstants.COMMISSION,
                entry
        ));
    }

    @Override
    public Consignment saveConsignment(Consignment consignment) {
        return repository.save(consignment);
    }

    @Override
    public Consignment getConsignmentById(Long id) {
        return getConsigmentByIdOrThrow(id);
    }

    @Override
    public List<Consignment> getConsignmentByConsignorAndVehicle(Long consignorId, Long vehicleId) {
        Customer consignor=customerManager.getById(consignorId);
        Vehicle vehicle=vehicleManager.getVehicleById(vehicleId);
        return repository.findByConsignorAndVehicle(consignor,vehicle);
    }


    @Override
    public Consignment updateConsignment(Long id,  BigDecimal minimumPrice, double commission, Address address) {
        Consignment toUpdate=getConsigmentByIdOrThrow(id);
        toUpdate.setAddress(address);
        if(!Objects.equals(toUpdate.getMinimumPrice(), minimumPrice)){
            Vehicle vehicle=vehicleManager.getVehicleById(toUpdate.getVehicle().getId());
            vehicle.setPrice(minimumPrice);
            vehicleManager.updateVehicle(vehicle.getId(), vehicle);
            toUpdate.setMinimumPrice(minimumPrice);
        }
        toUpdate.setCommission(commission);
        toUpdate.setAddress(address);
        return repository.save(toUpdate);
    }

    @Override
    public void deleteConsignment(Long consignmentId) {
        Consignment toDelete=getConsigmentByIdOrThrow(consignmentId);
        repository.delete(toDelete);
    }

    @Override
    public byte[] generateAgreement(Long consignmentId) {
        Consignment toAgreement=getConsigmentByIdOrThrow(consignmentId);
        return contractAdapter.documentCreator(toAgreement);
    }

    private Consignment getConsigmentByIdOrThrow(Long id){
        return repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Consignação com id: "+id+" não encontrada!"));
    }
}
