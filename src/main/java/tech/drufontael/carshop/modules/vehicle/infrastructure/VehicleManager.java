package tech.drufontael.carshop.modules.vehicle.infrastructure;

import tech.drufontael.carshop.modules.vehicle.domain.Brand;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleManager {

    //Manage Vehicle

    Vehicle createVehicle(Long modelId, String plate, String chassi, String renavan, Integer manufactureYear, String color);
    void addVariablesDatas(Long id,Long ownerId,BigDecimal price,Integer mileage);
    void addAdditionalInformations(Long id, String[] informations);
    Vehicle getVehicleById(Long id);
    Vehicle updateVehicle(Long id,Vehicle vehicle);
    void deleteVehicleById(Long id);

    //Manage VehicleModel

    VehicleModel createVehicleModel(String model, Integer modelYear, Long brandId);
    VehicleModel getVehicleModelById(Long id);
    List<VehicleModel> getAllVehicleModels();
    VehicleModel updateVehicleModel(Long id,VehicleModel model);
    void deleteVehicleModel(Long id);

    //Manage Brand

    Brand createBrand(String brand);
    Brand getBrandById(Long id);
    List<Brand> getAllBrand();
    Brand updateBrand(Long id,Brand brand);
    void deleteBrandById(Long id);
}
