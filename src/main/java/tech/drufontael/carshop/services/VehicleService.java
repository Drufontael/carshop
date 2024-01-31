package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.exceptions.ResourceAlreadyExistsException;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Image;
import tech.drufontael.carshop.model.Vehicle;
import tech.drufontael.carshop.repositories.ImageRepository;
import tech.drufontael.carshop.repositories.VehicleRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;
    @Autowired
    private ImageRepository imageRepository;




    public Vehicle create(Vehicle vehicle){
        var plate=Utils.plateValidation(vehicle.getPlate());
        try {
            read(plate);
            throw new ResourceAlreadyExistsException("Vehicle with license plate "+plate+" already has registration");
        }catch (ResourseNotFoundException e){
            vehicle.setPlate(plate);
        }
        return repository.save(vehicle);
    }

    public Vehicle read(String id){
        return repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Vehicle not found"));

    }

    public Vehicle update(String id,Object source){
        var target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Vehicle not found."));
        Utils.copyNonNullProperties(source,target);
        return repository.save(target);
    }

    public void delete(String id){
        var vehicle=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Vehicle not found"));
        repository.delete(vehicle);
    }

    public List<Vehicle> findAll(){
        return repository.findAll();
    }

    public void addImage(String plate, Image obj){
        Image image=imageRepository.save(obj);
        var vehicle=read(plate);
        vehicle.getImages().add(image);
        repository.save(vehicle);
    }


    public List<Image> findImages(String plate) {
        var vehicle=read(plate);
        return vehicle.getImages();
    }
}
