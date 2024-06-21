import { Vehicles } from './../../model/vehicles';
import { Component } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { CommonModule } from '@angular/common';

import { ConsignmentService } from '../../service/consignment.service';
import { ConsignorService } from '../../service/consignor.service';
import { VehicleService } from '../../service/vehicle.service';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { Consignors } from '../../model/consignors';

@Component({
  selector: 'app-consignment-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatCardModule,
    MatToolbarModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    MatOptionModule,
    MatSelectModule,
    CommonModule
  ],
  templateUrl: './consignment-form.component.html',
  styleUrl: './consignment-form.component.scss'
})
export class ConsignmentFormComponent {


  form = this.formBuilder.group({
    id: [''],
    consignorId: ['',[Validators.required]],
    vehiclePlate: ['',[Validators.required]],
    location: [''],
    date: [''],
    entryTime: [''],
    opened: [true],

  })

  consignors: Consignors[] = [];
  vehicles: Vehicles[] = []//this.vehicleService.findAll();



  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: ConsignmentService,
    private consignorService: ConsignorService,
    private vehicleService: VehicleService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute
  ) {
    this.consignorService.findAll().subscribe(response => this.consignors = response);
    this.vehicleService.findAll().subscribe(response => this.vehicles = response);
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.service.findById(id).subscribe(consignment => {
        this.consignorService.findByRegister(consignment.consignorId).subscribe(response => consignment.consignor = response.name)
        this.vehicleService.findByPlate(consignment.vehiclePlate).subscribe(response => {
          consignment.vehicle = response.brand + ' ' + response.modelCar
        })
        this.form.setValue(consignment)
      })
    }
  }

  onCancel() {
    this.location.back();
  }
  onSubmit() {
    if (this.form.value.date === '') {
      this.form.value.date = this.validarData(this.form.value.date)
    }
    if (this.form.value.entryTime===''){
      this.form.value.entryTime=this.validarHora(this.form.value.entryTime)
    }

    //console.log(this.form.value);
    this.service.save(this.form.value).subscribe(
      () => this.onSucess(),
      () => this.onError()
    )
  }

  onSucess() {
    this._snackBar.open('Consignação salva', '', { duration: 3000 })
    this.location.back()
  }

  onError() {
    this._snackBar.open('Falha em salvar a consignação!', '', { duration: 3000 })
  }

  validarData(inputDate: string | null | undefined) {

    if (inputDate === "") {

      var dataAtual = new Date();
      var dia = String(dataAtual.getDate()).padStart(2, '0');
      var mes = String(dataAtual.getMonth() + 1).padStart(2, '0'); // Mês é base 0
      var ano = dataAtual.getFullYear();
      return ano + '-' + mes + '-' + dia;
    }
    return ''
  }

  validarHora(inputTime: string) {

    if (inputTime === "") {
      var timeAtual = new Date();
      var hora = String(timeAtual.getHours()).padStart(2, '0');
      var minutos = String(timeAtual.getMinutes()).padStart(2, '0');
      return hora + ":" + minutos;
    }
    return ''
  }


}
