package tech.drufontael.carshop.modules.vehicle.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.drufontael.carshop.exceptions.ResourceNotFoundException;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerManager;
import tech.drufontael.carshop.modules.vehicle.domain.Brand;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleData;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;
import tech.drufontael.carshop.modules.vehicle.domain.enuns.Color;
import tech.drufontael.carshop.modules.vehicle.domain.value_object.*;
import tech.drufontael.carshop.modules.vehicle.infrastructure.BrandRepository;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleManager;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleModelRepository;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService implements VehicleManager {

    private final VehicleModelRepository modelRepository;
    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final CustomerManager customerManager;

    @Override
    @Transactional
    public Vehicle createVehicle(Long modelId, String plate, String chassi, String renavan, Integer manufactureYear, String color) {
        VehicleModel model = getVehicleModelById(modelId);

        VehicleData data = VehicleData.builder()
                .vehicleModel(model)
                .chassi(new Chassi(chassi))
                .plate(new Plate(plate))
                .renavam(new Renavam(renavan))
                .manufactureYear(new ManufactureYear(manufactureYear))
                .color(Color.valueOf(color))
                .build();

        Customer owner=customerManager.getById(1L);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleData(data);
        vehicle.setOwner(owner);
        vehicle.setPrice(BigDecimal.ZERO);
        vehicle.setMileage(0);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void addVariablesDatas(Long id, Long ownerId, BigDecimal price, Integer mileage) {
        Vehicle vehicle=getVehicleOrThrow(id);
        Customer customer = customerManager.getById(ownerId);
        customerManager.addType(customer.getId(), "OWNER");
        vehicle.setOwner(customer);
        vehicle.setPrice(price);
        vehicle.setMileage(mileage);
        vehicleRepository.save(vehicle);
    }


    @Override
    public void addAdditionalInformations(Long id, String[] informations) {
        Vehicle vehicle = getVehicleOrThrow(id);
        vehicle.getAdditionalInformations().addAll(Arrays.asList(informations));
        vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return getVehicleOrThrow(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle v = getVehicleOrThrow(id);
        v.setVehicleData(vehicle.getVehicleData());
        v.setPrice(vehicle.getPrice());
        v.setOwner(vehicle.getOwner());
        v.setMileage(vehicle.getMileage());
        v.setAdditionalInformations(vehicle.getAdditionalInformations());
        return vehicleRepository.save(v);
    }

    @Override
    public void deleteVehicleById(Long id) {
        Vehicle vehicle = getVehicleOrThrow(id);
        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleModel createVehicleModel(String model, Integer modelYear, Long brandId) {
        Brand brand = getBrandOrThrow(brandId);

        VehicleModel newModel = VehicleModel.builder()
                .brand(brand)
                .model(model)
                .modelYear(new ModelYear(modelYear))
                .build();

        return modelRepository.save(newModel);
    }

    @Override
    public VehicleModel getVehicleModelById(Long id) {
        return getVehicleModelOrThrow(id);
    }

    @Override
    public List<VehicleModel> getVehicleModelsByBrandId(Long brandId) {
        Brand brand=null;
        if(brandId!=null) brand=getBrandById(brandId);
        return modelRepository.findByBrand(brand);
    }

    @Override
    public VehicleModel updateVehicleModel(Long id, VehicleModel model) {
        VehicleModel modelToUpdate=getVehicleModelById(id);
        modelToUpdate.setModel(model.getModel());
        modelToUpdate.setModelYear(model.getModelYear());
        modelToUpdate.setBrand(model.getBrand());
        return modelRepository.save(modelToUpdate);
    }

    @Override
    public void deleteVehicleModel(Long id) {
        VehicleModel modelToDelete=getVehicleModelOrThrow(id);
        modelRepository.delete(modelToDelete);
    }

    @Override
    public Brand createBrand(String brand) {
        String brandName=Brand.formatBrand(brand);
        return brandRepository.findByName(brandName).orElseGet(() -> brandRepository.save(new Brand(brand)));
    }

    @Override
    public Brand getBrandById(Long id) {
        return getBrandOrThrow(id);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand updateBrand(Long id, Brand brand) {
        Brand brandToUpdade=getBrandOrThrow(id);
        brandToUpdade.setName(brand.getName());
        return brandRepository.save(brandToUpdade);
    }

    @Override
    public void deleteBrandById(Long id) {
        Brand brandToDelete=getBrandOrThrow(id);
        brandRepository.delete(brandToDelete);
    }


    private Vehicle getVehicleOrThrow(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo com id: " + id + " não encontrado"));
    }
    private VehicleModel getVehicleModelOrThrow(Long id){
        return modelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Modelo com id: "+id+" não encontrado"));
    }
    private Brand getBrandOrThrow(Long id){
        return brandRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Marca com id: "+id+" não encontrada"));
    }
}
