package tech.drufontael.carshop.modules.vehicle.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.drufontael.carshop.modules.shared.utils.ControllerUtils;
import tech.drufontael.carshop.modules.vehicle.api.dto.requets.VariableDataRequest;
import tech.drufontael.carshop.modules.vehicle.api.dto.requets.VehicleModelRequest;
import tech.drufontael.carshop.modules.vehicle.api.dto.requets.VehicleRequest;
import tech.drufontael.carshop.modules.vehicle.api.dto.responses.VehicleResponse;
import tech.drufontael.carshop.modules.vehicle.domain.Brand;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleManager;

import java.net.URI;

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


}
