package tech.drufontael.carshop.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import tech.drufontael.carshop.dto.ConsignorDto;
import tech.drufontael.carshop.model.Consignor;

import java.util.List;

@Tag(name = "Consignante", description = "Controlador de cadastro de consignantes")
public interface ConsignorDoc {
    @Operation(summary = "Create",method = "Post",description = "Cadastra um consignante.")
    public ResponseEntity<Consignor> create(ConsignorDto dto);

    @Operation(method = "Get",summary = "Read",description = "Retorna um consignante pelo CPF")
    public ResponseEntity<Consignor> findByRegister(String register);

    @Operation(method = "Get",summary = "Find all",description = "Retorna a lista de consignantes")
    public ResponseEntity<List<Consignor>> findAll();

    @Operation(method = "Put",summary = "Update",description = "Atualiza um consignante")
    public ResponseEntity<Consignor> update(String register);

    @Operation(method = "Delete",summary = "Delete",description = "Exclui um consignante")
    public ResponseEntity delete(String register);
}
