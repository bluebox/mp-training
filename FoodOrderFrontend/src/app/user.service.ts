import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './interface';
import { Customer } from './interfaces/customer';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private urlCustomer:string="http://127.0.0.1:8000/customer/"

  constructor(private http:HttpClient) { }

  getUser():Observable<Customer[]>{
    return this.http.get<Customer[]>(this.urlCustomer)
  }
  

}
