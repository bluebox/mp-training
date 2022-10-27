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
  url = "http://127.0.0.1:8000/"
  
  getUser(){
    return this.http.get(this.url+'user/')
  }

  getBranch(){
    return this.http.get(this.url+'branch/')
  }
  addBranch(data : any){
    return this.http.post<any>(this.url+'newbranch/',data)
  }

  getCurrentBranch(id : any){
    return this.http.get(`http://127.0.0.1:8000/getbranch/${id}`)
  }

  updateBranches(id : any,data:any){
    return this.http.post(`http://127.0.0.1:8000/updatebranch/${id}`,data)
  }

  deleteBranches(data : any){
    return this.http.post(this.url+'delete/',data)
  }
  getSearchBranches(text:any){
    return this.http.get(this.url+'searchbranch?search='+text)
  }



  getServices(){
    return this.http.get(this.url+'services')
  }
  addService(data : any){
    return this.http.post<any>(this.url+'services/',data)
  }
  getCurrentService(id:any){
    return this.http.get(`http://127.0.0.1:8000/getservice/${id}`)
  }
  updateServices(id:any,data:any){
    return this.http.post(`http://127.0.0.1:8000/updateservice/${id}`,data)
  }
  deleteServices(data:any){
    return this.http.post(this.url+'deleteservice/',data)
  }
  getSearchServices(text:any){
    return this.http.get(this.url+'searchservice?search='+text)
  }


  
 
  clientRegister(data : any){
    return this.http.post<any>(this.url+'clientregistration',data)
  }
  getClients(){
    return this.http.get(this.url+'listclients')
  }




  getEmployee(){
    return this.http.get(this.url+'listemployees')
  }
  getlastEmployee(){
    return this.http.get(this.url+'empbranch')
  }
  newEmployee(data : any){
    return this.http.post<any>(this.url+'employeeregistration',data)
  }
  getHairStylist(){
    return this.http.get(this.url+'employeelist')
  }



  newAppointment(data:any){
    return this.http.post<any>(this.url+'newappointment',data)
  }
  getappointments(){
    return this.http.get(this.url+'appointments/')
  }
  getCurrentAppointment(id:any){
    return this.http.get(`http://127.0.0.1:8000/getappointment/${id}`)
  }
  updateAppointment(id:any,data:any){
    return this.http.post(`http://127.0.0.1:8000/updateappointment/${id}`,data)
  }
  getConfirmAppointments(){
    return this.http.get(this.url+'confirmappointment')
  }
  getCompleteAppointmentas(){
    return this.http.get(this.url+'completeappointment')
  }
  getRejectAppointments(){
    return this.http.get(this.url+'rejorcancel')
  }



  getSearchEmployees(text:any){
    return this.http.get(this.url+'serachemp?search='+text)
  }
 
} 
