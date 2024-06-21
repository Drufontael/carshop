import { Component, Input, Output,EventEmitter } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Consignors } from '../../model/consignors';
import { MatTooltip } from '@angular/material/tooltip';


@Component({
  selector: 'app-consignor-list',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTooltip
  ],
  templateUrl: './consignor-list.component.html',
  styleUrl: './consignor-list.component.scss'
})
export class ConsignorListComponent {

  @Input() consignors:Consignors[]=[];
  @Output() add=new EventEmitter(false);
  @Output() edit=new EventEmitter(false);
  @Output() delete=new EventEmitter(false);
  displayedColumns=['name','register','phone','actions']

  onAdd(){
    return this.add.emit(true);
  }

  onEdit(id:string){
    return this.edit.emit(id);
  }

  onDelete(id:string){
    return this.delete.emit(id);
  }

}
