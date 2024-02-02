package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.dto.ConsignmentDto;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Consignment;
import tech.drufontael.carshop.repositories.ConsignmentRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConsignmentService {
    @Autowired
    private ConsignmentRepository repository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ConsignorService consignorService;
    
    

    public ConsignmentDto create(ConsignmentDto consignment){
        var consignor=consignorService.read(consignment.getConsignorRegister());
        var vehicle=vehicleService.read(consignment.getVehiclePlate());
        var newConsignment = consignment.toConsignment();
        newConsignment.setConsignor(consignor.toConsignor());
        newConsignment.setVehicle(vehicle.toVehicle());
        return new ConsignmentDto(repository.save(newConsignment));
    }

    public ConsignmentDto read(UUID id){
        return new ConsignmentDto(repository.findById(id)
                .orElseThrow(()->new ResourseNotFoundException("Consignment not found")));
    }

    public ConsignmentDto update(UUID id, Object source){
        var target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found."));
        Utils.copyNonNullProperties(source,target);
        return new ConsignmentDto(repository.save(target));
    }

    public void delete(UUID id){
        Consignment consignment=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found"));
        repository.delete(consignment);
    }

    public List<ConsignmentDto> findAll(){
        List<ConsignmentDto> dtos=new ArrayList<>();
        for(Consignment consignment:repository.findAll()){
            dtos.add(new ConsignmentDto(consignment));
        }
        return dtos;
    }


}
