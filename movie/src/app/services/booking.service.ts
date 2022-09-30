import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  public url:string="http://127.0.0.1:8000/Booking/"

  constructor(private http:HttpClient) { }
  postData(data:any){
    return this.http.post(this.url,data)
  }
}
