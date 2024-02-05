package tech.drufontael.carshop.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import tech.drufontael.carshop.dto.ImageDto;
import tech.drufontael.carshop.dto.VehicleDto;

import java.util.List;

@Tag(name = "Veículos",description = "Controlador dos veículos")
public interface VehicleDoc {

    @Operation(summary = "Create",description = "Cadastra um veiculo")
    ResponseEntity<VehicleDto> create(VehicleDto obj);

    @Operation(summary = "List All",description = "Lista todos os veiculos cadastrados")
    ResponseEntity<List<VehicleDto>> findall();


    @Operation(summary = "Read",description = "Retorna um veículo pela placa")
    ResponseEntity<VehicleDto> findById(String plate);

    @Operation(summary = "Update",description = "Atualiza um veiculo")
    ResponseEntity<VehicleDto> update(String plate, VehicleDto dto);


    @Operation(summary = "Delete",description = "Exclui um veiculo pela placa")
    ResponseEntity delete(String plate);

    @Operation(summary = "Add image", description = "Adiciona uma imagem a um veiculo")
    ResponseEntity addImage(String plate, ImageDto dto);


    @Operation(summary = "Image List",description = "Lista todas imagens de um veiculo")
    ResponseEntity<List<ImageDto>> findImages(String plate);



}
