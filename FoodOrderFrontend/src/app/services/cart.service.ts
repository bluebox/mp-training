import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../interfaces/employee';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  urlAdd=""
  constructor(private http:HttpClient) { }


  getCart():Observable<any>{
    return this.http.get<any>(this.urlAdd)
  }


  postEmp(data:object)
  {
    return this.http.post<any>(this.urlAdd,data)
  }
}
