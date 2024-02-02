package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.dto.ImageDto;
import tech.drufontael.carshop.dto.VehicleDto;
import tech.drufontael.carshop.exceptions.ResourceAlreadyExistsException;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Image;
import tech.drufontael.carshop.model.Vehicle;
import tech.drufontael.carshop.repositories.ImageRepository;
import tech.drufontael.carshop.repositories.VehicleRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;
    @Autowired
    private ImageRepository imageRepository;




    public VehicleDto create(VehicleDto dto){
        var plate=Utils.plateValidation(dto.getPlate());
        var vehicle=dto.toVehicle();
        try {
            read(plate);
            throw new ResourceAlreadyExistsException("Vehicle with license plate "+plate+" already has registration");
        }catch (ResourseNotFoundException e){
            vehicle.setPlate(plate);
        }
        return new VehicleDto(repository.save(vehicle));
    }

    public VehicleDto read(String id){
        return new VehicleDto(repository.findById(id)
                .orElseThrow(()->new ResourseNotFoundException("Vehicle not found")));
    }

    public VehicleDto update(String id,Object source){
        var target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Vehicle not found."));
        Utils.copyNonNullProperties(source,target);
        return new VehicleDto(repository.save(target));
    }

    public void delete(String id){
        var vehicle=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Vehicle not found"));
        repository.delete(vehicle);
    }

    public List<VehicleDto> findAll(){
       List<Vehicle> vehicles=repository.findAll();
       List<VehicleDto> dtos=new ArrayList<>();
       for (Vehicle vehicle:vehicles){
           dtos.add(new VehicleDto(vehicle));
       }
       return dtos;
    }

    public void addImage(String plate, ImageDto obj){
        Image image=imageRepository.save(obj.toImage());
        var vehicle=repository.findById(plate).orElseThrow(()->new ResourseNotFoundException("Vehicle not found."));
        vehicle.getImages().add(image);
        repository.save(vehicle);
    }


    public List<ImageDto> findImages(String plate) {
        var vehicle=repository.findById(plate).orElseThrow(()->new ResourseNotFoundException("Vehicle not found."));
        List<ImageDto> dtos=new ArrayList<>();
        for(Image image:vehicle.getImages()){
            dtos.add(new ImageDto(image));
        }
        return dtos;
    }
}
