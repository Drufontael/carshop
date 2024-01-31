package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Consignment;
import tech.drufontael.carshop.repositories.ConsignmentRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.List;
import java.util.UUID;

@Service
public class ConsignmentService {
    @Autowired
    private ConsignmentRepository repository;
    
    

    public Consignment create(Consignment consignment){
        return repository.save(consignment);
    }

    public Consignment read(UUID id){
        return repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found"));
    }

    public Consignment update(UUID id, Object source){
        Consignment target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found."));
        Utils.copyNonNullProperties(source,target);
        return repository.save(target);
    }

    public void delete(UUID id){
        Consignment consignment=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignment not found"));
        repository.delete(consignment);
    }

    public List<Consignment> findAll(){
        return repository.findAll();
    }


}
