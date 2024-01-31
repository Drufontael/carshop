package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Consignor;
import tech.drufontael.carshop.repositories.ConsignorRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.List;

@Service
public class ConsignorService {
    @Autowired
    private ConsignorRepository repository;
    
    

    public Consignor create(Consignor consignor){
        return repository.save(consignor);
    }

    public Consignor read(String id){
        return repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignor not found"));
    }

    public Consignor update(String id,Object source){
        Consignor target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignor not found."));
        Utils.copyNonNullProperties(source,target);
        return repository.save(target);
    }

    public void delete(String id){
        Consignor consignor=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignor not found"));
        repository.delete(consignor);
    }

    public List<Consignor> findAll(){
        return repository.findAll();
    }


}
