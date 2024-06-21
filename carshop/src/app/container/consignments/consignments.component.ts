import { Vehicles } from './../../model/vehicles';
import { CommonModule, Location } from '@angular/common';
import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ConsignmentService } from '../../service/consignment.service';
import { VehicleService } from '../../service/vehicle.service';
import { ConsignorService } from '../../service/consignor.service';
import { Consignors } from '../../model/consignors';
import { Consignment } from '../../model/consignment';
import { ConsignmentListComponent } from '../../component/consignment-list/consignment-list.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UUID } from 'crypto';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../shared/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-consignments',
  standalone: true,
  imports: [
    MatCardModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    CommonModule,
    ConsignmentListComponent
  ],
  templateUrl: './consignments.component.html',
  styleUrl: './consignments.component.scss'
})
export class ConsignmentsComponent {


  vehicle:Partial<Vehicles>={}
  consignor:Partial<Consignors>={}
  consignments:Consignment[]=[]
  consignments$:Observable<Consignment[]>

  constructor(
    private service:ConsignmentService,
    private vehicleService:VehicleService,
    private consignorService:ConsignorService,
    private router:Router,
    private route:ActivatedRoute,
    private dialog:MatDialog

  ){
    this.consignments$=this.service.findAll();
    this.consignments$.subscribe(list=>{
      this.consignments=list
      for(let consignment of this.consignments){
        consignorService.findByRegister(consignment.consignorId).subscribe(response=>{
          consignment.consignor=response.name
        })
        vehicleService.findByPlate(consignment.vehiclePlate).subscribe(response=>{
          consignment.vehicle=response.brand+' '+response.modelCar
        })
      }
    })
  }

  onAdd(){
    this.router.navigate(['new'],{relativeTo:this.route})
  }

  onEdit(id:UUID){
    this.router.navigate([this.route,id]);
  }

  onDelete(id:UUID){
    this.dialog.open(ConfirmationDialogComponent,{data:'Confirma a remoção da consignação?'}).afterClosed()
    .subscribe(result=>{
      if(result){
        this.service.delete(id).subscribe(ok=>window.location.reload());
      }
    })


  }

  onDownload(id:UUID) {
    this.router.navigate([this.route,'pdf',id]);
    }

}
