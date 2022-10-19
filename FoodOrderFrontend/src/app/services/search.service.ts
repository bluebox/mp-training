import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../interfaces/food';
import { Restaurant } from '../interfaces/restaurant';
import { Search } from '../interfaces/searchResult';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private url:string="http://127.0.0.1:8000/search/"


  constructor(private http:HttpClient) { }



  getSearch(item:string):Observable<any[]>{
    return this.http.get<any[]>(this.url + item)
  }
}
