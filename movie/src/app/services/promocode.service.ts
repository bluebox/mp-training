import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PromocodeService {
 public url:string="http://127.0.0.1:8000/Promocode/"
  constructor(private http:HttpClient) { }
  GetCodes(){
    return this.http.get(this.url)
  }
}
