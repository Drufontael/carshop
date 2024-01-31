package tech.drufontael.carshop.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.model.Vehicle;
import tech.drufontael.carshop.utils.Utils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
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

    public Vehicle toVehicle() {
        Vehicle newObj=new Vehicle();
        Utils.copyNonNullProperties(this,newObj);
        return newObj;
    }
}
