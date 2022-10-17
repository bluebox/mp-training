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
  addBranch(data : any){
    return this.http.post<any>('http://127.0.0.1:8000/newbranch/',data)
  }

  getCurrentBranch(id : any){
    return this.http.get(`http://127.0.0.1:8000/getbranch/${id}`)
  }

  updateBranches(id : any,data:any){
    return this.http.post(`http://127.0.0.1:8000/updatebranch/${id}`,data)
  }

  deleteBranches(data : any){
    return this.http.post('http://127.0.0.1:8000/delete/',data)
  }
  getSearchBranches(text:any){
    return this.http.get('http://127.0.0.1:8000/searchbranch?search='+text)
  }



  getServices(){
    return this.http.get(' http://127.0.0.1:8000/services')
  }
  addService(data : any){
    return this.http.post<any>('http://127.0.0.1:8000/services/',data)
  }
  getCurrentService(id:any){
    return this.http.get(`http://127.0.0.1:8000/getservice/${id}`)
  }
  updateServices(id:any,data:any){
    return this.http.post(`http://127.0.0.1:8000/updateservice/${id}`,data)
  }
  deleteServices(data:any){
    return this.http.post('http://127.0.0.1:8000/deleteservice/',data)
  }
  getSearchServices(text:any){
    return this.http.get('http://127.0.0.1:8000/searchservice?search='+text)
  }


  
 
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

  newAppointment(data:any){
    return this.http.post<any>('http://127.0.0.1:8000/newappointment',data)
  }
  getappointments(){
    return this.http.get('http://127.0.0.1:8000/appointments/')
  }
  getSearchEmployees(text:any){
    return this.http.get('http://127.0.0.1:8000/serachemp?search='+text)
  }
 
  // getBranchFilter(text:any){
  //   return this.http.get<any>('http://127.0.0.1:8000/search?s='+text)
  // }
} 
