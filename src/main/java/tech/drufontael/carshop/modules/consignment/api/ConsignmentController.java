package tech.drufontael.carshop.modules.consignment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;
import tech.drufontael.carshop.modules.consignment.api.requests.ConsignmentRequest;
import tech.drufontael.carshop.modules.consignment.api.responses.ConsignmentResponse;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.consignment.infrastructure.ConsignmentManager;
import tech.drufontael.carshop.modules.shared.utils.ControllerUtils;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v2/consignments")
@RequiredArgsConstructor
public class ConsignmentController {

    private final ConsignmentManager manager;


    @PostMapping
    public ResponseEntity<ConsignmentResponse> createConsignment(@RequestBody ConsignmentRequest request){
        Consignment consignment=manager.createConsignment(request.consignorId(), request.vehicleId(), request.address());
        URI location= ControllerUtils.buildUriFromCurrentRequest(consignment.getId());
        return ResponseEntity.created(location).body(ConsignmentResponse.fromDomain(consignment));
    }

    @GetMapping
    public ResponseEntity<List<ConsignmentResponse>> getConsignmentByConsignorAndVehicle(
            @RequestParam(value = "consignanteId",required = false) Long consignorId,
            @RequestParam(value = "veiculoId",required = false) Long vehicleId){
        List<Consignment> consignments=manager.getConsignmentByConsignorAndVehicle(consignorId,vehicleId);
        return ResponseEntity.ok(consignments.stream().map(ConsignmentResponse::fromDomain).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsignmentResponse> getConsignmentById(@PathVariable Long id){
        return ResponseEntity.ok(ConsignmentResponse.fromDomain(manager.getConsignmentById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsignmentResponse> updateConsignmentById(@PathVariable Long id,@RequestBody ConsignmentRequest request){
        try{
            BigDecimal minimumPriceParse=new BigDecimal(request.minimumPrice());
            Double commissionParse=Double.parseDouble(request.commission());
            Consignment consignment=manager.updateConsignment(id,  minimumPriceParse, commissionParse,request.address());
            return ResponseEntity.ok(ConsignmentResponse.fromDomain(consignment));
        }catch (NumberFormatException e){
            e.printStackTrace();
            throw new InvalidArgumentFormatException("Valores invalidos para comissão ou preço!");
        }
    }


}
