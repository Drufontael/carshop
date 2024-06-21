import { Vehicles } from '../model/vehicles';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private readonly API:string='/api/vehicles'

  constructor(private httpClient:HttpClient) { }

  findAll(){
    return this.httpClient.get<Vehicles[]>(this.API);
  }

  findByPlate(plate:string){
    return this.httpClient.get<Vehicles>(this.API+'/'+plate);
  }

  submit(plate:string,vehicle:Partial<Vehicles>){
    if(plate){
      return this.update(plate,vehicle)
    }
    return this.create(vehicle)
  }

  private create(vehicle:Partial<Vehicles>){
    return this.httpClient.post(this.API,vehicle);
  }

  private update(plate:string|undefined,vehicle:Partial<Vehicles>){
    return this.httpClient.put(this.API+'/'+plate,vehicle);
  }

  delete(plate:string){
    return this.httpClient.delete(this.API+'/'+plate);
  }
}
