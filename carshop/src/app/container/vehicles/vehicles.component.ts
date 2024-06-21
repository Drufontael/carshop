import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import { Vehicles } from '../../model/vehicles';
import { MatToolbarModule } from '@angular/material/toolbar';
import { VehiclesListComponent } from '../../component/vehicles-list/vehicles-list.component';
import { VehicleService } from '../../service/vehicle.service';
import { Observable } from 'rxjs';
import { CommonModule, NgIf } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationDialogComponent } from '../../shared/confirmation-dialog/confirmation-dialog.component';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-vehicles',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    VehiclesListComponent,
    MatProgressSpinnerModule,
    NgIf,
    CommonModule,
    ConfirmationDialogComponent
  ],
  templateUrl: './vehicles.component.html',
  styleUrl: './vehicles.component.scss'
})
export class VehiclesComponent {

  vehicles$:Observable<Vehicles[]>;
  displayedColumns=["brand","modelCar","year","modelYear","km","plate","price"]

  constructor(
    private service:VehicleService,
    private router:Router,
    private route:ActivatedRoute,
    private dialog:MatDialog
     ){
    this.vehicles$=service.findAll();
  }

  onAdd() {
    this.router.navigate(['new'],{relativeTo:this.route});
    }

  onDelete(plate:string){
    const dialogRef=this.dialog.open(ConfirmationDialogComponent,{data:'Confirma a remoção do veículo?'})
    dialogRef.afterClosed().subscribe(result=>{
      if(result) this.service.delete(plate).subscribe(ok=>this.vehicles$=this.service.findAll());
    })


  }

  onEdit(plate:string){
    this.router.navigate([plate],{relativeTo:this.route})
  }

  toExpenses(plate:string) {
    this.router.navigate(['expenses',plate]);
    }

}
