import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consignors } from '../model/consignors';
import { Consignment } from '../model/consignment';
import { UUID } from 'crypto';

@Injectable({
  providedIn: 'root'
})
export class ConsignmentService {
  private readonly API='api/consignments'

  constructor(
    private httpClient:HttpClient
  ) { }

  findAll(){
    return this.httpClient.get<Consignment[]>(this.API);
  }

  findById(id:string){
    return this.httpClient.get<Consignment>(this.API+'/'+id);
  }

  save(consignment:Partial<Consignment>){
    console.log(consignment)
    return this.httpClient.post<Consignment>(this.API,consignment);
  }

  delete(id:UUID){
    return this.httpClient.delete(this.API+'/'+id);
  }

}
