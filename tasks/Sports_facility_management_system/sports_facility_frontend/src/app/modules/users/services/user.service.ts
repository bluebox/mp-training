import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loginurl: string='http://localhost:8000/login';
  private userbokingsurl: string='http://localhost:8000/get-user-bookings/'
  private cancelBooking:string='http://127.0.0.1:8000/cancel-booking/'
  constructor( private http:HttpClient) { }


  UserLogin(data: any){
    
    return this.http.post(this.loginurl,data)
  }

  GetUserBookings(uid:any){

    return this.http.get(this.userbokingsurl+uid)

  }

  cancelUserBookings(bid:any){
    return this.http.post(this.cancelBooking+bid,{})

  }


}
