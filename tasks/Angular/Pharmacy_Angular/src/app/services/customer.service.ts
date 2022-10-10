import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http : HttpClient) { }
  
  postCustomer(data:any){
    return this.http.post<any>("http://localhost:8000/customer/",data);
  }

  getCustomer(){
    return this.http.get<any>("http://localhost:8000/customer/");
  }

  putCustomer(data:any,id:string){
    return this.http.put<any>("http://localhost:8000/customer/"+id,data);

  }

  deleteCustomer(id:string){
    return this.http.delete<any>("http://localhost:8000/customer/"+id);
  }



}
