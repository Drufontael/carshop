import { Component } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ConsignorService } from '../../service/consignor.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-consignor-form',
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
  templateUrl: './consignor-form.component.html',
  styleUrl: './consignor-form.component.scss'
})
export class ConsignorFormComponent {


  form = this.formBuilder.group({
    id:[''],
    name: [''],
    address: [''],
    register: [''],
    identity: [''],
    phone: ['']
  })

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: ConsignorService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id']
    if (id) {
      this.service.findByRegister(id).subscribe(consignor =>
        this.form.setValue(consignor)
      )
    }
  }

  onCancel() {
    this.location.back();
  }

  onSubmit() {
    this.service.save(this.form.value).subscribe(()=>this.onSucess(),()=>this.onError);
  }

  onSucess() {
    this._snackBar.open('Consignante salvo', '', { duration: 3000 })
    this.location.back()
  }

  onError() {
    this._snackBar.open('Falha em salvar o consignante!', '', { duration: 3000 })
  }

}
