package tech.drufontael.carshop.modules.vehicle.domain;

import jakarta.persistence.*;
import lombok.*;
import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.ManufactureYear;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.Chassi;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.Plate;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.Renavan;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class VehicleData {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelo_id")
    protected VehicleModel vehicleModel;
    @Column(length = 7)
    protected Plate plate;
    @Column(length = 17)
    protected Chassi chassi;
    @Column(length = 11)
    protected Renavan renavan;
    protected ManufactureYear manufactureYear;

    private void validaAno(ManufactureYear manufactureYear, VehicleModel vehicleModel){
        if(manufactureYear.getValue()< vehicleModel.modelYear.getValue()-1){
            throw new InvalidArgumentFormatException("Ano de fabricação incoerente: "+this.manufactureYear);
        }
        if(manufactureYear.getValue()> vehicleModel.modelYear.getValue()){
            throw new InvalidArgumentFormatException("Ano de fabricação incoerente: "+this.manufactureYear);
        }
    }

    public void setManufactureYear(ManufactureYear manufactureYear){
        if(this.vehicleModel !=null) validaAno(manufactureYear,this.vehicleModel);
        this.manufactureYear = manufactureYear;
    }

    public void setVehicleModel(VehicleModel vehicleModel){
        if(this.manufactureYear !=null) validaAno(this.manufactureYear, vehicleModel);
        this.vehicleModel = vehicleModel;
    }


}
