package tech.drufontael.carshop.modules.vehicle.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.drufontael.carshop.modules.shared.utils.ControllerUtils;
import tech.drufontael.carshop.modules.vehicle.api.dto.requets.VariableDataRequest;
import tech.drufontael.carshop.modules.vehicle.api.dto.requets.VehicleModelRequest;
import tech.drufontael.carshop.modules.vehicle.api.dto.requets.VehicleRequest;
import tech.drufontael.carshop.modules.vehicle.api.dto.responses.VehicleModelResponse;
import tech.drufontael.carshop.modules.vehicle.api.dto.responses.VehicleResponse;
import tech.drufontael.carshop.modules.vehicle.domain.Brand;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleManager;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v2/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleManager manager;

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody VehicleRequest request){
        Vehicle vehicle=manager.createVehicle(
                request.modelId(),
                request.plate(),
                request.chassi(),
                request.renavan(),
                request.manufactureYear(),
                request.color()
        );
        URI location = ControllerUtils.buildUriFromCurrentRequest(vehicle.getId());
        return ResponseEntity.created(location).body(VehicleResponse.fromDomain(vehicle));
    }

    @PostMapping("/{id}/variables-datas")
    public ResponseEntity<?> addVariableData(@PathVariable Long id,@RequestBody VariableDataRequest request){
        manager.addVariablesDatas(id, request.ownerId(), request.price(), request.mileage());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/additional-informations")
    public ResponseEntity<?> addAdditionalInformations(@PathVariable Long id,@RequestBody String[] informations){
        manager.addAdditionalInformations(id,informations);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestParam("marca") String brand){
        Brand newBrand=manager.createBrand(brand);
        URI location = ControllerUtils.buildUriFromCurrentRequest(newBrand.getId());
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/models")
    public ResponseEntity<?> createModel(@RequestBody VehicleModelRequest request){
        VehicleModel model=manager.createVehicleModel(request.model(),request.modelYear(), request.brandId());
        URI location = ControllerUtils.buildUriFromCurrentRequest(model.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles(){
        return ResponseEntity.ok(manager.getAllVehicles().stream().map(VehicleResponse::fromDomain).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleByID(@PathVariable Long id){
        return ResponseEntity.ok(VehicleResponse.fromDomain(manager.getVehicleById(id)));
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands(){
        return ResponseEntity.ok(manager.getAllBrand());
    }
    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id){
        return ResponseEntity.ok(manager.getBrandById(id));
    }

    @GetMapping("/models")
    public ResponseEntity<List<VehicleModelResponse>> getAllModels(@RequestParam(value = "marcaId",required = false) Long id){
        return ResponseEntity.ok(manager.getVehicleModelsByBrandId(id).stream()
                .map(VehicleModelResponse::fromDomain).toList());
    }
    @GetMapping("/models/{id}")
    public ResponseEntity<VehicleModelResponse> getVehicleModelById(@PathVariable Long id){
        return ResponseEntity.ok(VehicleModelResponse.fromDomain(manager.getVehicleModelById(id)));
    }


}
