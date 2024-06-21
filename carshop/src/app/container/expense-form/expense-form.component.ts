import { Component } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { ExpenseService } from '../../service/expense.service';
import { Expenses } from '../../model/expenses';
import { response } from 'express';


@Component({
  selector: 'app-expense-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatCardModule,
    MatToolbarModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule
  ],
  templateUrl: './expense-form.component.html',
  styleUrl: './expense-form.component.scss'
})
export class ExpenseFormComponent {


  form = this.formBuilder.group({
    id: [undefined],
    plate: [''],
    description: [''],
    date: [''],
    valor: [0]
  })
  plate: string | undefined;
  expense: Partial<Expenses> = {}
  id: string | undefined;

  constructor(
    private service: ExpenseService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private formBuilder: NonNullableFormBuilder
  ) {
    this.plate = this.route.snapshot.params['plate'],
    this.id = this.route.snapshot.params['id']
  }

  ngOnInit(): void {
    if (this.id) {
      this.service.findById(this.id).subscribe(response => this.form.setValue(response))
    }
  }

  onCancel() {
    this.location.back();
  }
  onSubmit() {
    this.expense = this.form.value;
    this.expense.plate = this.plate;
    this.service.submit(this.id, this.expense).subscribe(() => this.onSucess(), () => this.onError);;
  }

  onSucess() {
    this._snackBar.open('Despesa salva', '', { duration: 3000 })
    this.location.back()
  }

  onError() {
    this._snackBar.open('Falha em salvar a despesa!', '', { duration: 3000 })
  }

}
