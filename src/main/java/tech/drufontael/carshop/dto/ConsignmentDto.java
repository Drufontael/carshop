package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import tech.drufontael.carshop.model.Consignment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsignmentDto  extends RepresentationModel<ConsignmentDto> {
    private UUID id;
    private UUID consignorId;
    private String vehiclePlate;
    private String location;
    private LocalDate date;
    private LocalTime entryTime;
    private boolean opened;

    public ConsignmentDto(Consignment consignment){
        BeanUtils.copyProperties(consignment,this);
        setConsignorId(consignment.getConsignor().getId());
        setVehiclePlate(consignment.getVehicle().getPlate());
    }

    public Consignment toConsignment(){
        return new Consignment(getId(),null,null,getLocation(),getDate()
                ,getEntryTime(),isOpened());
    }
}
