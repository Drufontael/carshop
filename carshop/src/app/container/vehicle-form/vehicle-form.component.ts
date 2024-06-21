import { Component } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { VehicleService } from '../../service/vehicle.service';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-vehicle-form',
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
  templateUrl: './vehicle-form.component.html',
  styleUrl: './vehicle-form.component.scss'
})
export class VehicleFormComponent {


  form = this.formBuilder.group({
    brand: [''],
    modelCar: [''],
    km: [undefined],
    plate: ['',[Validators.required,Validators.maxLength(7),Validators.maxLength(8)]],
    year: [undefined],
    modelYear: [undefined],
    chassis: [''],
    renavan: [''],
    ownerName: [''],
    ownerIdentity: [''],
    price: [undefined],
    manual: false,
    extraKey: false,
    tools: false,
    dut: false,
    yearDocument: [undefined]

  })
  plate:string

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: VehicleService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute
  ) {
    this.plate = this.route.snapshot.params['plate'];

  }

  ngOnInit(): void{


    if (this.plate) {
      this.service.findByPlate(this.plate).subscribe(response =>this.form.setValue(response));
    }
  }

  onCancel() {
    this.location.back();
  }
  onSubmit() {
    this.service.submit(this.plate,this.form.value).subscribe(()=>this.onSucess(),()=>this.onError);
  }

  onSucess() {
    this._snackBar.open('Veiculo salvo', '', { duration: 3000 })
    this.location.back()
  }

  onError() {
    this._snackBar.open('Falha em salvar o veículo!', '', { duration: 3000 })
  }

  getErrorMessage(fieldName:string){
    const field=this.form.get(fieldName)
    if(field?.hasError('required')){
      return 'Este campo deve ser preenchido'
    }
    return ''
  }



}
