package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.dto.ConsignorDto;
import tech.drufontael.carshop.exceptions.ResourceNotFoundException;
import tech.drufontael.carshop.model.Consignor;
import tech.drufontael.carshop.repositories.ConsignorRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConsignorService {
    @Autowired
    private ConsignorRepository repository;
    
    

    public ConsignorDto create(ConsignorDto consignor){
        return new ConsignorDto(repository.save(consignor.toConsignor()));
    }

    public ConsignorDto read(UUID id){
        return new ConsignorDto(repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Consignor not found")));
    }

    public ConsignorDto update(UUID id,Object source){
        var target=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Consignor not found."));
        Utils.copyNonNullProperties(source,target);
        return new ConsignorDto(repository.save(target));
    }

    public void delete(UUID id){
        var consignor=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Consignor not found"));
        repository.delete(consignor);
    }

    public List<ConsignorDto> findAll(){
        List<ConsignorDto> dtos=new ArrayList<>();
        for (Consignor consignor:repository.findAll()){
            dtos.add(new ConsignorDto(consignor));
        }
        return dtos;
    }


}
