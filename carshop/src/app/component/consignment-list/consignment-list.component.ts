import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Consignment } from '../../model/consignment';
import { MatTooltip } from '@angular/material/tooltip';

@Component({
  selector: 'app-consignment-list',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTooltip
  ],
  templateUrl: './consignment-list.component.html',
  styleUrl: './consignment-list.component.scss'
})
export class ConsignmentListComponent {



  @Input() consignments: Consignment[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  @Output() download = new EventEmitter(false);
  displayedColumns = ['consignor', 'vehicle', 'vehiclePlate', 'actions'];

  onDelete(id: string) {
    this.delete.emit(id);
  }
  onEdit(id: string) {
    this.edit.emit(id);
  }
  onAdd() {
    this.add.emit(true);
  }
  onDownload(id: string) {
    this.download.emit(id);
  }




}
