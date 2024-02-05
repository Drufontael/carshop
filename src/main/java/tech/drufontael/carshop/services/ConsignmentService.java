package tech.drufontael.carshop.services;

import org.springframework.beans.BeanUtils;
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

    public ConsignmentDto update(UUID id, ConsignmentDto source){
        var target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found."));
        Utils.copyNonNullProperties(source,target);
        if(source.getConsignorRegister()!=null) target.setConsignor(consignorService.read(source.getConsignorRegister())
                .toConsignor());
        if(source.getVehiclePlate()!=null) target.setVehicle(vehicleService.read(source.getVehiclePlate())
                .toVehicle());
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

    public List<ConsignmentDto> findByConsignorRegister(String register){
        consignorService.read(register);
        List<ConsignmentDto> list=new ArrayList<>();
        for(Consignment consignment:repository.findByConsignorRegister(register)){
            list.add(new ConsignmentDto(consignment));
        }
        return list;
    }

    public ConsignmentDto findByVehiclePlate(String plate){
        vehicleService.read(plate);
        return new ConsignmentDto(repository.findByVehiclePlate(plate));
    }


}
