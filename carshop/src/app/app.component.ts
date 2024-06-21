import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { VehiclesComponent } from './container/vehicles/vehicles.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MatToolbarModule,
    VehiclesComponent,
    MatButtonModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'carshop';
  constructor(
    private router:Router
  ){}
  onVehicles(){
    this.router.navigate(['/vehicles'])
  }
  onConsignors(){
    this.router.navigate(['/consignors'])
  }

  onConsignment(){
    this.router.navigate(['/consignments'])
  }
}
