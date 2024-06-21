import { Observable } from 'rxjs';
import { DEFAULT_CURRENCY_CODE, LOCALE_ID,Component, EventEmitter, Input, Output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Vehicles } from '../../model/vehicles';
import { MatIconModule } from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { CurrencyPipe } from '@angular/common';
import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import {MatTooltipModule} from '@angular/material/tooltip'

registerLocaleData(ptBr);

@Component({
  selector: 'app-vehicles-list',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    CurrencyPipe,
    MatTooltipModule
  ],
  providers:[
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL' },
  ],
  templateUrl: './vehicles-list.component.html',
  styleUrl: './vehicles-list.component.scss'
})
export class VehiclesListComponent {



  @Input() vehicles:Vehicles[] = [];
  @Output() add=new EventEmitter(false);
  @Output() delete=new EventEmitter(false);
  @Output() edit=new EventEmitter(false);
  @Output() expense=new EventEmitter(false);
  displayedColumns = ["brand", "modelCar", "year", "km", "plate", "price","actions"];

  onAdd() {
    this.add.emit(true);
  }

  onDelete(plate: string) {
    this.delete.emit(plate);
  }
  onEdit(plate:string) {
    this.edit.emit(plate);
  }
  toExpense(plate: string) {
    this.expense.emit(plate);
    }


}
