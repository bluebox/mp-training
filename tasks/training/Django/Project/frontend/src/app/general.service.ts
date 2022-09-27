import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const baseUrl ='http://localhost:8000/';



@Injectable({
  providedIn: 'root'
})

export class GeneralService {


  constructor(private http: HttpClient) { }

  register_owner(data : any){
    return this.http.post(baseUrl + 'owner/',data)
  }
  register_customer(data : any){
    return this.http.post(baseUrl + 'customer/',data)
  }
  add_vehicle(data : any){
    return this.http.post(baseUrl + 'vehicle/', data)
  }
  get_vehicle(){
    return this.http.get(baseUrl + 'vehicle/')
  }
}


