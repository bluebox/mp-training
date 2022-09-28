import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) { }

  registerCustomer(data: any){
    return this.http.post<any>('http://127.0.0.1:8000/users/register-customer/',data)
  }
  registerEmployee(data:any){
    return this.http.post<any>('http://127.0.0.1:8000/users/register-employee/', data)

  }
  getUsers(){
    return this.http.get('http://127.0.0.1:8000/users/register-customer/')
  }
  getBranches(){
    return this.http.get('http://127.0.0.1:8000/users/branch/')

  }
  loginUser(data:any){
    return this.http.post<any>('http://127.0.0.1:8000/users/login/',data)
  }

  }
