package tech.drufontael.carshop.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import tech.drufontael.carshop.dto.ConsignorDto;

import java.util.List;
import java.util.UUID;

@Tag(name = "Consignante", description = "Controlador de cadastro de consignantes")
public interface ConsignorDoc {
    @Operation(summary = "Create",method = "Post",description = "Cadastra um consignante.")
    ResponseEntity<ConsignorDto> create(ConsignorDto dto);

    @Operation(method = "Get",summary = "Read",description = "Retorna um consignante pelo CPF")
    ResponseEntity<ConsignorDto> findById(UUID id);

    @Operation(method = "Get",summary = "Find all",description = "Retorna a lista de consignantes")
    ResponseEntity<List<ConsignorDto>> findAll();

    @Operation(method = "Put",summary = "Update",description = "Atualiza um consignante")
    ResponseEntity<ConsignorDto> update(UUID id, ConsignorDto obj);

    @Operation(method = "Delete",summary = "Delete",description = "Exclui um consignante")
    ResponseEntity delete(UUID id);
}
