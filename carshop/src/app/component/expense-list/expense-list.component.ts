import { Component,DEFAULT_CURRENCY_CODE, LOCALE_ID, EventEmitter, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Expenses } from '../../model/expenses';
import { CurrencyPipe, DatePipe } from '@angular/common';
import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { MatTooltip } from '@angular/material/tooltip';

registerLocaleData(ptBr);

@Component({
  selector: 'app-expense-list',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    CurrencyPipe,
    MatTooltip,
    DatePipe
  ],
  providers:[
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL' },
  ],
  templateUrl: './expense-list.component.html',
  styleUrl: './expense-list.component.scss'
})
export class ExpenseListComponent {
  @Input() expenses:Expenses[]=[];
  @Output() add=new EventEmitter(false);
  @Output() edit=new EventEmitter(false);
  @Output() delete=new EventEmitter(false);
  displayedColumns=['date','description','valor','actions']

  onAdd(){
    return this.add.emit(true);
  }

  onEdit(id:string){
    return this.edit.emit(id);
  }

  onDelete(id:string){
    return this.delete.emit(id);
  }

  getTotalValue(){
    return this.expenses.map(t => t.valor).reduce((acc, value) => acc + value, 0);
  }


}
