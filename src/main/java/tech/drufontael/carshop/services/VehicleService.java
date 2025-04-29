package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.dto.VehicleDto;
import tech.drufontael.carshop.exceptions.ResourceAlreadyExistsException;
import tech.drufontael.carshop.exceptions.ResourceNotFoundException;
import tech.drufontael.carshop.model.Vehicle;
import tech.drufontael.carshop.repositories.VehicleRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;


    public VehicleDto create(VehicleDto dto){
        var plate=Utils.plateValidation(dto.getPlate());
        var vehicle=dto.toVehicle();
        try {
            read(plate);
            throw new ResourceAlreadyExistsException("Vehicle with license plate "+plate+" already has registration");
        }catch (ResourceNotFoundException e){
            vehicle.setPlate(plate);
        }
        vehicle.setActive(true);
        return new VehicleDto(repository.save(vehicle));
    }

    public VehicleDto read(String id){
        return new VehicleDto(repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Vehicle not found")));
    }

    public VehicleDto update(String id,Object source){
        var target=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vehicle not found."));
        Utils.copyNonNullProperties(source,target);
        return new VehicleDto(repository.save(target));
    }

    public void delete(String id){
        var vehicle=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vehicle not found"));
        repository.delete(vehicle);
    }


    public  List<VehicleDto> findAll(Boolean... var){
        if(var.length==0) return this.findAll();
        List<VehicleDto> dtoList=new ArrayList<>();
        List<Vehicle> list=var[0]?repository.findByActiveTrue():repository.findByActiveFalse();
        for(Vehicle vehicle:list){
            dtoList.add(new VehicleDto(vehicle));
        }
        return dtoList;
    }


    private List<VehicleDto> findAll(){
       List<Vehicle> vehicles=repository.findAll();
       List<VehicleDto> dtos=new ArrayList<>();
       for (Vehicle vehicle:vehicles){
           dtos.add(new VehicleDto(vehicle));
       }
       return dtos;
    }

    public void addImage(String plate,String description,String path){
        Vehicle vehicle=repository.findById(plate).orElseThrow(()->new ResourceNotFoundException("Vehicle not found"));
        vehicle.getImages().put(description,path);
        repository.save(vehicle);
    }

    public Map<String,String> getImages(String plate){
        Vehicle vehicle=repository.findById(plate).orElseThrow(()->new ResourceNotFoundException("Vehicle not found"));
        return vehicle.getImages();
    }

    public String getImageByDescription(String plate,String description){
        Vehicle vehicle=repository.findById(plate).orElseThrow(()->new ResourceNotFoundException("Vehicle not found"));
        return vehicle.getImages().get(description);
    }

    public void deleteImage(String plate,String description){
        Vehicle vehicle=repository.findById(plate).orElseThrow(()->new ResourceNotFoundException("Vehicle not found"));
        vehicle.getImages().remove(description);
    }

    public void updateImage(String plate,String description,String newPath){
        Vehicle vehicle=repository.findById(plate).orElseThrow(()->new ResourceNotFoundException("Vehicle not found"));
        vehicle.getImages().put(description,newPath);
        repository.save(vehicle);
    }


}
