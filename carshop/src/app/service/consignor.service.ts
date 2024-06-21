import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Consignors } from '../model/consignors';

@Injectable({
  providedIn: 'root'
})
export class ConsignorService {

  private readonly API='api/consignors'

  constructor(
    private httpClient:HttpClient
  ) { }

  findAll(){
    return this.httpClient.get<Consignors[]>(this.API);
  }

  findByRegister(id:string){
    return this.httpClient.get<Consignors>(this.API+'/'+id);
  }

  save(consignor:Partial<Consignors>){
    return this.httpClient.post<Consignors>(this.API,consignor);
  }

  delete(register:string){
    return this.httpClient.delete(this.API+'/'+register);
  }





}
