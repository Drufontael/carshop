package tech.drufontael.carshop.modules.consignment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.exceptions.ResourceNotFoundException;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.consignment.infrastructure.ConsignmentManager;
import tech.drufontael.carshop.modules.consignment.infrastructure.ConsignmentRepository;
import tech.drufontael.carshop.modules.consignment.infrastructure.adapter.PdfContractAdapter;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerManager;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.shared.value_object.CEP;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleManager;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsignmentService implements ConsignmentManager {

    private final ConsignmentRepository repository;
    private final VehicleManager vehicleManager;
    private final CustomerManager customerManager;
    private final PdfContractAdapter contractAdapter;

    @Override
    public Consignment createConsignment(Long consignorId, Long vehicleId, Address address) {
        Customer consignor=customerManager.getById(consignorId);
        Vehicle vehicle=vehicleManager.getVehicleById(vehicleId);
        LocalDateTime entry=LocalDateTime.now();
        if(address==null){
            address= Address.builder()
                    .cep(new CEP("74215-240"))
                    .street("Avenida Multirão")
                    .complement("nº 2496, Qd. 78, Lt. 23E, Loja 01")
                    .neighborhood("St. Bueno")
                    .city("Goiânia")
                    .state("GO")
                    .country("Brasil")
                    .build();
        }
        return repository.save(new Consignment(
                null,
                consignor,
                vehicle,
                address,
                entry
        ));
    }

    @Override
    public Consignment getConsignmentById(Long id) {
        return getConsigmentByIdOrThrow(id);
    }

    @Override
    public List<Consignment> getAllConsignment() {
        return repository.findAll();
    }

    @Override
    public Consignment updateConsignment(Long id, Consignment consignment) {
        Consignment toUpdate=getConsigmentByIdOrThrow(id);
        toUpdate.setConsignor(consignment.getConsignor());
        toUpdate.setVehicle(consignment.getVehicle());
        toUpdate.setAddress(consignment.getAddress());
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
