import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookingInterface } from '../interface/booking';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  public url:string="http://127.0.0.1:8000/Booking/"

  constructor(private http:HttpClient) { }
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
  getTicket(id:number){
    return this.http.get<BookingInterface>("http://127.0.0.1:8000/ticket/"+id)
  }
  getBookingHistory(id:number){
    return this.http.get<BookingInterface[]>("http://127.0.0.1:8000/history/"+id)
  }
  cancelBooking(id:number){
    return this.http.post("http://127.0.0.1:8000/cancel/"+id+"/","")
  }
}
