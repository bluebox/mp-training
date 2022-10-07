import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Token } from '@angular/compiler';

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
  getOwner(id : any){
    return this.http.get(baseUrl + 'owner/'+id +'/')
  }
  updateOwnerProfile(data : any){
    return this.http.put(baseUrl + 'owner/'+ data.owner_id +'/', data)
  }

  getprofileDetails(){
    let customer = window.sessionStorage.getItem('token');

    let token = customer?.split(':')[1];
    // console.log(token?.substring(1, token.length));
    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));
    return this.http.get(baseUrl + 'customerdetails/',
      { 'headers': headers }
      );
  }


  customerLogin(data: any)
  {
    return this.http.post(baseUrl + 'customer-login/', data)
  }

  registerCustomer(data : any){
    return this.http.post(baseUrl + 'customer/',data)
  }
  updateCustomerProfile(data : any ){
    let customer = window.sessionStorage.getItem('token');
    let token = customer?.split(':')[1];
    console.log(token);
    // console.log(token?.substring(1, token.length));
    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));

    return this.http.put(baseUrl + 'customerdetails/', data, { 'headers': headers })
  }
  getCustomer(id : any){
    return this.http.get(baseUrl + 'customer/'+ id + '/')
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

  bookVehicle(data : any){
    let customer = window.sessionStorage.getItem('token');
    let token = customer?.split(':')[1];
    console.log(token);
    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));

  return this.http.post(baseUrl + 'trip/', data, { 'headers': headers })
  }

  orderHistory(){
    let customer = window.sessionStorage.getItem('token');
    let token = customer?.split(':')[1];
    console.log(token);
    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));

    return this.http.get(baseUrl + 'trip/', { 'headers': headers })
  }

  logOutCustomer(){
    return this.http.delete(baseUrl + 'logout-customer/')
  }
  logOutOwner(){
    return this.http.delete(baseUrl + 'logout-owner/')
  }
}


