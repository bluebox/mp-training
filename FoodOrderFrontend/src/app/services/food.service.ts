import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../interfaces/food';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private url:string="http://127.0.0.1:8000/food/"
  private url1:string="http://127.0.0.1:8000/restaurant/foodlist/"
  constructor(private http:HttpClient) { }

  getFood():Observable<Food[]>{
    return this.http.get<Food[]>(this.url)
  }

  getOneFood(id:string):Observable<Food[]>{
    return this.http.get<Food[]>(this.url + id + "/")
  }
 postFood(data:object)
 {
   return this.http.post<Food>(this.url,data)
 }

 getFoodRes(id:string):Observable<Food[]>{
  return this.http.get<Food[]>(this.url1 + id + "/")
 }

 delOneFood(id:string):Observable<Food[]>{
  return this.http.delete<Food[]>(this.url + id + "/")
}
}