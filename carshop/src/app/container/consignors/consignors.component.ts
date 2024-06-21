import { Component } from '@angular/core';
import { ConsignorService } from '../../service/consignor.service';
import { Observable } from 'rxjs';
import { Consignors } from '../../model/consignors';
import { ConsignorListComponent } from '../../component/consignor-list/consignor-list.component';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../shared/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-consignors',
  standalone: true,
  imports: [
    ConsignorListComponent,
    MatCardModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    CommonModule
  ],
  templateUrl: './consignors.component.html',
  styleUrl: './consignors.component.scss'
})
export class ConsignorsComponent {



  consignors$: Observable<Consignors[]>

  constructor(
    private service: ConsignorService,
    private router: Router,
    private route: ActivatedRoute,
    private dialog:MatDialog
  ) {
    this.consignors$ = service.findAll();
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route })
  }

  onEdit(id:string) {
    console.log(this.route);
    this.router.navigate([this.route,id])
  }

  onDelete(id:string){
    this.dialog.open(ConfirmationDialogComponent,{data:'Confirma a remoção do consignante?'}).afterClosed()
    .subscribe(result=>{
      if(result){
        this.service.delete(id).subscribe(ok=>this.consignors$=this.service.findAll());
      }
    })
  }

}
