import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../interfaces/cart';
import { Employee } from '../interfaces/employee';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  urlAdd="http://127.0.0.1:8000/customer/cart/"
  constructor(private http:HttpClient) { }


  getCart():Observable<Cart[]>{
    return this.http.get<Cart[]>(this.urlAdd)
  }


  postEmp(data:object)
  {
    return this.http.post<any>(this.urlAdd,data)
  }


  postCart(data:any)
  {
    return this.http.post<any>(this.urlAdd,data)
  }

  delItem(data:any)
  {
    console.log("in cart service")
    return this.http.delete<any>(this.urlAdd+data)
  }
  
}
