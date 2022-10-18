import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../interfaces/food';
import { Restaurant } from '../interfaces/restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private url:string="http://127.0.0.1:8000/restaurant/"
  private resUrl:string="http://127.0.0.1:8000/restaurant/signinn/"
  constructor(private http:HttpClient) { }

  getRes():Observable<Restaurant[]>{
    return this.http.get<Restaurant[]>(this.url)
  }

  getOneFood(id:string):Observable<any[]>{
    return this.http.get<any[]>(this.url + id + "/")
  }

  postUser(data:object)
  {
    console.log("psoting......")
    return this.http.post<any>(this.resUrl,data)
  }

  getOneRes(id:string):Observable<any[]>{
    return this.http.get<any[]>(this.url + id + "/")
  }
}
