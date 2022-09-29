import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const baseUrl ='http://localhost:8000/';



@Injectable({
  providedIn: 'root'
})

export class GeneralService {


  constructor(private http: HttpClient) { }

  ownerLogin(data: any)
  {
    return this.http.post(baseUrl + 'owner-login', data)
  }
  registerOwner(data : any){
    return this.http.post(baseUrl + 'owner/',data)
  }
  updateOwnerProfile(id : any ){
    return this.http.get(baseUrl + 'customer/' + id)
  }



  customerLogin(data: any)
  {
    return this.http.post(baseUrl + 'customer-login/', data)
  }

  registerCustomer(data : any){
    return this.http.post(baseUrl + 'customer/',data)
  }
  updateCustomerProfile(id : any ){
    return this.http.get(baseUrl + 'customer/' + id)
  }




  addVehicle(data : any){
    return this.http.post(baseUrl + 'vehicle/', data)
  }
  getVehicle(){
    return this.http.get(baseUrl + 'vehicle/')
  }
  getOwnerVehicle(data : any){
    return this.http.get(baseUrl + 'owner-vehicle?owner_id=' + data)
  }
  deleteVehicle(id : any ){
    return this.http.delete(baseUrl + 'delete-vehicle/' + id )
  }
}


