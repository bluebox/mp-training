import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private http: HttpClient) { }

  getAverageRatingAndTotalRatings(){
    return this.http.get('/api/bookings/getAverageRating/')
  }

  // getAdventureTours() {
  //   return this.http.get('/api/tours/tours_list')
  // }

  filterTours(tour_type: string){
    return this.http.get(`/api/tours/tours_by_type/${tour_type}`)
  }

  getTourById(id: number) {
    return this.http.get(`/api/tours/tours_list/${id}`)
  }

  getFeedBacks(){
    return this.http.get('/api/bookings/feedbacks/')
  }
  postFeedbackApi(feedbackObj:any){
    return this.http.post('/api/bookings/postfeedback/', feedbackObj)
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
  getBookingOfUser(id: number){
    return this.http.get(`/api/bookings/booking/${id}`)
  }

  uploadImage(file: any){
    const formData = new FormData();
    console.log("file", file, file.name);
    // Store form name as "file" with file data
    formData.append("file", file);
    console.log(formData);
    // Make http post request over api
    // with formData as req
    return this.http.post('/api/bookings/uploadImage/', formData)
  }

  postEnquiry(enquiryObj:any){
    return this.http.post('/api/tours/enquiries/', enquiryObj)
  }

  getPackage(id:number){
    return this.http.get(`/api/tours/packageDetailed/${id}`)
  }

  cancelBookingByUser(data:any, id: number){
    return this.http.put(`/api/bookings/booking/${id}`, data)
  }


}
