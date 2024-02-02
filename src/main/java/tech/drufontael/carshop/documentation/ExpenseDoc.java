package tech.drufontael.carshop.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import tech.drufontael.carshop.dto.ExpenseDto;
import tech.drufontael.carshop.model.Expense;

import java.util.List;
import java.util.UUID;

@Tag(name = "Despesas",description = "Controlador de despesas dos veiculos")
public interface ExpenseDoc {

    @Operation(summary = "Create",description = "Cadastra uma despesa")
    public ResponseEntity<ExpenseDto> create(ExpenseDto obj);

    @Operation(summary = "List",description = "Lista todas as despesas")
    public ResponseEntity<List<ExpenseDto>> findall();

    @Operation(summary = "Read",description = "Retorna uma despesa pelo seu identificador")
    public ResponseEntity<ExpenseDto> findById(UUID id);

    @Operation(summary = "List by plate",description = "Retorna as despesas de um veiculo pela sua placa")
    public ResponseEntity<List<ExpenseDto>> findExpensesByPlate(String plate);

}
