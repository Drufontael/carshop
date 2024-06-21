import { NgIf, CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { VehiclesListComponent } from '../../component/vehicles-list/vehicles-list.component';
import { Expenses } from '../../model/expenses';
import { VehicleService } from '../../service/vehicle.service';
import { ExpenseService } from '../../service/expense.service';
import { ExpenseListComponent } from '../../component/expense-list/expense-list.component';
import { Vehicles } from '../../model/vehicles';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../shared/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-expenses',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    VehiclesListComponent,
    MatProgressSpinnerModule,
    NgIf,
    CommonModule,
    ExpenseListComponent
  ],
  templateUrl: './expenses.component.html',
  styleUrl: './expenses.component.scss'
})
export class ExpensesComponent {


  expenses: Expenses[] = []
  vehicle: Partial<Vehicles> = {}

  constructor(
    private vehicleService: VehicleService,
    private service: ExpenseService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog
  ) {
    const plate = this.route.snapshot.params['plate'];
    this.vehicleService.findByPlate(plate).subscribe(response => this.vehicle = response);
    this.service.findByPlate(plate).subscribe(response => this.expenses = response)
  }

  onEdit(id: string) {
    this.router.navigate([id], { relativeTo: this.route });
  }
  onDelete(id: string) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, { data: 'Confirma a remoção da despesa?' })
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.service.delete(id).subscribe();
        window.location.reload()
      }
    })

  }
  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

}
