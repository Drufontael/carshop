package tech.drufontael.carshop.modules.vehicle.infrastructure;

import tech.drufontael.carshop.modules.vehicle.domain.Brand;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;

import java.math.BigDecimal;

public interface VehicleManager {
    Vehicle createVehicle(Long modelId, String plate, String chassi, String renavan, Integer manufactureYear);
    void addOwner(Long id, Long ownerId);
    void addPrice(Long id, BigDecimal price);
    void addMileage(Long id, Integer mileage);
    void addAdditionalInformations(Long id, String[] informations);
    Vehicle getVehicleById(Long id);
    Vehicle updateVehicle(Long id,Vehicle vehicle);
    void deleteVehicleById(Long id);
    VehicleModel createVehicleModel(String model, Integer modelYear, Long brandId);
    Brand createBrand(String brand);
}
