package tech.drufontael.carshop.modules.vehicle.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.drufontael.carshop.exceptions.ResourceNotFoundException;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.vehicle.domain.Brand;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleData;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.*;
import tech.drufontael.carshop.modules.vehicle.infrastructure.BrandRepository;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleManager;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleModelRepository;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService implements VehicleManager {

    private final VehicleModelRepository modelRepository;
    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;


    @Override
    @Transactional
    public Vehicle createVehicle(Long modelId, String plate, String chassi, String renavan, Integer manufactureYear) {
        VehicleModel model=modelRepository.findById(modelId)
                .orElseThrow(()->new ResourceNotFoundException("Modelo não encontrado no id: "+modelId));
        VehicleData data=VehicleData.builder()
                .vehicleModel(model)
                .chassi(new Chassi(chassi))
                .plate(new Plate(plate))
                .renavan(new Renavan(renavan))
                .manufactureYear(new ManufactureYear(manufactureYear))
                .build();
        Vehicle vehicle=new Vehicle();
        vehicle.setVehicleData(data);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void addOwner(Long id, Customer owner) {

    }

    @Override
    public void addPrice(Long id, BigDecimal price) {
            vehicleRepository.findById(id).ifPresentOrElse(vehicle -> {
                vehicle.setPrice(price);
                vehicleRepository.save(vehicle);
            },()->{throw new ResourceNotFoundException("Veiculo com id: "+id+" não encontrado");});
    }

    @Override
    public void addMilleage(Long id, Integer mileage) {
        vehicleRepository.findById(id).ifPresentOrElse(vehicle -> {
            vehicle.setMileage(mileage);
            vehicleRepository.save(vehicle);
        },()->{throw new ResourceNotFoundException("Veiculo com id: "+id+" não encontrado");});
    }

    @Override
    public void addAdditionalInformations(Long id, String[] informations) {
        vehicleRepository.findById(id).ifPresentOrElse(vehicle -> {
            vehicle.getAdditionalInformations().addAll(Arrays.stream(informations).toList());
            vehicleRepository.save(vehicle);
        },()->{throw new ResourceNotFoundException("Veiculo com id: "+id+" não encontrado");});
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Veiculo com id: "+id+" não encontrado"));
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        return vehicleRepository.findById(id).map(v->{
            v.setVehicleData(vehicle.getVehicleData());
            v.setPrice(vehicle.getPrice());
            v.setOwner(vehicle.getOwner());
            v.setMileage(vehicle.getMileage());
            v.setAdditionalInformations(vehicle.getAdditionalInformations());
            return vehicleRepository.save(v);
        }).orElseThrow(()->new ResourceNotFoundException("Veiculo com id: "+id+" não encontrado"));
    }

    @Override
    public void deleteVehicleById(Long id) {
        vehicleRepository.findById(id).ifPresentOrElse(
                vehicleRepository::delete,
                ()->{
                    throw new ResourceNotFoundException("Veiculo com id: "+id+" não encontrado");
                }
        );
    }

    @Override
    public VehicleModel createVehicleModel(String model, Integer modelYear, Long brandId) {
        Brand brand=brandRepository.findById(brandId)
                .orElseThrow(()-> new ResourceNotFoundException("Marca não encontrada no id: "+brandId));
        VehicleModel newModel=VehicleModel.builder()
                .brand(brand)
                .modelo(model)
                .modelYear(new ModelYear(modelYear))
                .build();
        return modelRepository.save(newModel);
    }

    @Override
    public Brand createBrand(String brand) {
        return brandRepository.findByName(brand).orElse(brandRepository.save(new Brand(brand)));
    }
}
