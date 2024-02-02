package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.drufontael.carshop.dto.ConsignorDto;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Consignor;
import tech.drufontael.carshop.repositories.ConsignorRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsignorService {
    @Autowired
    private ConsignorRepository repository;
    
    

    public ConsignorDto create(ConsignorDto consignor){
        return new ConsignorDto(repository.save(consignor.toConsignor()));
    }

    public ConsignorDto read(String id){
        return new ConsignorDto(repository.findById(id)
                .orElseThrow(()->new ResourseNotFoundException("Consignor not found")));
    }

    public ConsignorDto update(String id,Object source){
        var target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignor not found."));
        Utils.copyNonNullProperties(source,target);
        return new ConsignorDto(repository.save(target));
    }

    public void delete(String id){
        var consignor=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Consignor not found"));
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
