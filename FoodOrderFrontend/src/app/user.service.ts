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
  private urlCustomerOne:string="http://127.0.0.1:8000/customer/crud/"
  private urlCustomerSignin:string="http://127.0.0.1:8000/customer/signin/"

  constructor(private http:HttpClient) { }




  getUser():Observable<Customer[]>{
    return this.http.get<any[]>(this.urlCustomer)
  }
  
  postUser(data:object)
  {
    return this.http.post<any>(this.urlCustomerSignin,data)
  }

getSingleUser(id:any)
{
  return this.http.get<any[]>(this.urlCustomerOne+id+'/')
}

editUser(data:object,id:any){
    return this.http.post<any>(this.urlCustomerOne+id+'/',data)
  }

  delCus(id:string):Observable<any[]>{
    return this.http.delete<any[]>(this.urlCustomerOne + id + "/")
  }

}
