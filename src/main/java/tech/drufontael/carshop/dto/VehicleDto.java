package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import tech.drufontael.carshop.model.Vehicle;
import tech.drufontael.carshop.utils.Utils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto extends RepresentationModel<VehicleDto> {
    private String brand;
    private String modelCar;
    private Integer year;
    private Integer modelYear;
    private Integer km;
    private String plate;
    private String chassis;
    private String renavan;
    private String ownerName;
    private String ownerIdentity;
    private Integer price;
    private Boolean manual;
    private Boolean extraKey;
    private Boolean tools;
    private Boolean dut;
    private Integer yearDocument;
    private Double totalExpenses;

    public VehicleDto(Vehicle vehicle){
        BeanUtils.copyProperties(vehicle,this);
    }

    public Vehicle toVehicle() {
        Vehicle newObj=new Vehicle();
        Utils.copyNonNullProperties(this,newObj);
        return newObj;
    }
}
