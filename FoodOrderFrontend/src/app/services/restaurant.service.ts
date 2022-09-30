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
  constructor(private http:HttpClient) { }

  getRes():Observable<Restaurant[]>{
    return this.http.get<Restaurant[]>(this.url)
  }

  getOneFood(id:string):Observable<Restaurant[]>{
    return this.http.get<Restaurant[]>(this.url + id + "/")
  }

  postUser(data:object)
  {
    return this.http.post<Restaurant>(this.url,data)
  }

  getOneRes(id:string):Observable<Restaurant[]>{
    return this.http.get<Restaurant[]>(this.url + id + "/")
  }
}
