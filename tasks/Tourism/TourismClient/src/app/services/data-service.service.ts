import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private http: HttpClient) { }

  getAdventureTours() {
    return this.http.get('/api/tours/tours_list')
  }

  getTourById(id: number) {
    return this.http.get(`/api/tours/tours_list/${id}`)
  }

  getFeedBacks(){
    return this.http.get('/api/bookings/feedbacks/')
  }

  addPaymentDetails(paymentObj: any){
    console.log(paymentObj);
    return this.http.post('/api/bookings/payment/', paymentObj)

  }

  addBookingDetails(bookingObj: any){
    console.log(bookingObj);
    return this.http.post('/api/bookings/booking/', bookingObj)

  }
  getBookingListOfUser(){
    return this.http.get('/api/bookings/booking/')
  }



}
