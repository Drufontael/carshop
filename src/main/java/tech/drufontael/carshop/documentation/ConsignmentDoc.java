package tech.drufontael.carshop.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import tech.drufontael.carshop.dto.ConsignmentDto;

import java.util.List;
import java.util.UUID;

@Tag(name = "Consignação", description = "Controlador de cadastro de Consignações")
public interface ConsignmentDoc {
    @Operation(summary = "Create",method = "Post",description = "Cadastra um consignante.")
    ResponseEntity<ConsignmentDto> create(ConsignmentDto dto);

    @Operation(method = "Get",summary = "Read",description = "Retorna uma consignação pelo id")
    ResponseEntity<ConsignmentDto> findByRegister(UUID id);

    @Operation(method = "Get",summary = "Find all",description = "Retorna a lista de Consignações")
    ResponseEntity<List<ConsignmentDto>> findAll();

    @Operation(method = "Put",summary = "Update",description = "Atualiza uma consignação")
    ResponseEntity<ConsignmentDto> update(UUID id, ConsignmentDto obj);

    @Operation(method = "Delete",summary = "Delete",description = "Exclui uma consignação")
    ResponseEntity delete(UUID id);

    @Operation(method = "Get",summary = "Find by consignor",description = "Lista de consignações por Consignante")
    ResponseEntity<List<ConsignmentDto>> findByConsignorRegister(String register);

    @Operation(method = "Get", summary = "Find by vehicle",description = "Retorna a consignação de um veiculo")
    ResponseEntity<ConsignmentDto> findByVehiclePlate(String plate);
}
