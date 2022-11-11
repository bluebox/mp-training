import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookingInterface } from '../interface/booking';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  public url:string="http://127.0.0.1:8000/Booking/"

  constructor(private http:HttpClient) { }
  getBooking(id:number){
    return this.http.get("http://127.0.0.1:8000/all/"+id)
  }
  postPayment(data:any){
    return this.http.post("http://127.0.0.1:8000/Payment/",data)
  }
  postData(data:any){
    return this.http.post(this.url,data)
  }
  //get booking history filtered by hall number to get the previously selected seats
  getSelectedSeats(id:number){
    return this.http.get("http://127.0.0.1:8000/selectedseats/"+id)
  }
  getTicket(){
    return this.http.get<BookingInterface>("http://127.0.0.1:8000/ticket/")
  }
  getBookingHistory(data:string){
    return this.http.get<BookingInterface[]>("http://127.0.0.1:8000/history/"+data)
  }
  cancelBooking(bid:number){
    return this.http.post("http://127.0.0.1:8000/cancel/"+bid+"/","")
  }
  cancelHistory(){
    return this.http.get("http://127.0.0.1:8000/cancelled/")
  }
  
}
