import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Expenses } from '../model/expenses';
import { UUID } from 'crypto';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {



  private readonly API='api/expenses'

  constructor(
    private http:HttpClient
    ) { }

  findByPlate(plate:string){
    return this.http.get<Expenses[]>(this.API+'/'+plate+'/list');
  }

  submit(id: string|undefined, expense: Partial<Expenses>) {
    if(id) return this.update(id,expense)
    return this.create(expense)
  }

  findById(id: string) {
    return this.http.get<Expenses>(this.API+'/'+id);
  }

  private create(expense: Partial<Expenses>){
    return this.http.post<Partial<Expenses>>(this.API,expense);
  }

  private update(id:string|undefined,expense: Partial<Expenses>){
    return this.http.put<Partial<Expenses>>(this.API+'/'+id,expense)
  }

  delete(id: string) {
    return this.http.delete(this.API+'/'+id);
  }



}
