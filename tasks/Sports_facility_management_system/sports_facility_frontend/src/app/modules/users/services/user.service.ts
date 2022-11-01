import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loginurl: string='http://localhost:8000/login';
  private userbokingsurl: string='http://localhost:8000/get-user-bookings/'
  private cancelBooking:string='http://127.0.0.1:8000/cancel-booking/'
  private feedbackUrl:string='http://127.0.0.1:8000/update-feedback/'
  private userurl:string='http://127.0.0.1:8000/user?uid='
  private createUserurl:string='http://127.0.0.1:8000/user'
  private bookingdetailsurl:string='http://127.0.0.1:8000/bookingform?bid='
  private bookedEquipments:string='http://127.0.0.1:8000/Get-rented-equipments?bid='
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

  updateFeedback(bid:any,obj:any){
    return this.http.post(this.feedbackUrl+bid,obj)
  }
  getUserDetails(uid:number){
    return this.http.get(this.userurl+uid)
  }
  createUser(obj:any){
    return this.http.post(this.createUserurl,obj)
  }
  updateUser(uid:number,obj:any){
    return this.http.put(this.userurl+uid,obj)
  }
  getBookingDetails(bid:any){
    return this.http.get(this.bookingdetailsurl+bid)
  }
  getBookedEquipDetails(bid:any){
    return this.http.get(this.bookedEquipments+bid)
  }
  

}
