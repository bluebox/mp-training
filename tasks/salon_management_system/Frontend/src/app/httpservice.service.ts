import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class HttpserviceService {
  ClientRegistration(value: any) {
    throw new Error('Method not implemented.');
  }

  constructor(private http : HttpClient) {  }

  getUser(){
    return this.http.get('http://127.0.0.1:8000/user/')
  }

  getBranch(){
    return this.http.get('http://127.0.0.1:8000/branch/')
  }

  getServices(){
    return this.http.get(' http://127.0.0.1:8000/services')
  }

  
  // registerStudent(data: any){
  //   return this.http.post<any>('http://127.0.0.1:8000/userstudent',data)
  // }
  clientRegister(data : any){
    return this.http.post<any>('http://127.0.0.1:8000/clientregistration',data)
  }

  getClients(){
    return this.http.get('http://127.0.0.1:8000/listclients')
  }

  getEmployee(){
    return this.http.get('http://127.0.0.1:8000/listemployees')
  }

  newEmployee(data : any){
    return this.http.post<any>('http://127.0.0.1:8000/employeeregistration',data)
  }
  
  addBranch(data : any){
    return this.http.post<any>('http://127.0.0.1:8000/newbranch/',data)
  }

  addService(data : any){
    return this.http.post<any>('http://127.0.0.1:8000/services/',data)
  }
}
