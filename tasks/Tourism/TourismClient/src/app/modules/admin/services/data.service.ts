import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  getPlaces(){
    return this.http.get('/api/tours/places')
    // .pipe(
    //   map(res => {
    //     if(res)
    //       console.log(res);
    //     return res
    //   })
    // )
  }

  getCoupons(){
    return this.http.get('/api/tours/coupons')
    // .pipe(
    //   map(res => {
    //     if(res)
    //       console.log(res);
    //     return res
    //   })
    // )
  }
  getVehicles(){
    return this.http.get('/api/tours/vehicles')
    // .pipe(
    //   map(res => {
    //     if(res)
    //       console.log(res);
    //     return res
    //   })
    // )
  }

  addTour(tourObj:any) {
    return this.http.post('/api/tours/tours_list/', tourObj)
  }

  editTour(tourObj:any, id: number) {
    return this.http.put(`/api/tours/tours_list/${id}`, tourObj)
  }
  getToursList() {
    return this.http.get('/api/tours/tours_list/')
  }
  getTour(id:number) {
    return this.http.get(`/api/tours/tours_list/${id}`)
  }



  addCoupon(couponObj:any) {
    return this.http.post('/api/tours/coupons/', couponObj)
  }

  editCoupon(couponObj:any, id: number) {
    return this.http.put(`/api/tours/coupons/${id}`, couponObj)
  }
  getCouponsList() {
    return this.http.get('/api/tours/coupons/')
  }
  getCoupon(id:number) {
    return this.http.get(`/api/tours/coupons/${id}`)
  }




  addVehicle(vehicleObj:any) {
    return this.http.post('/api/tours/vehicles/', vehicleObj)
  }

  editVehicle(vehicleObj:any, id: number) {
    return this.http.put(`/api/tours/vehicles/${id}`, vehicleObj)
  }
  getVehiclesList() {
    return this.http.get('/api/tours/vehicles/')
  }
  getVehicle(id:number) {
    return this.http.get(`/api/tours/vehicles/${id}`)
  }



  addPlace(placeObj:any) {
    return this.http.post('/api/tours/places/', placeObj)
  }
  editPlace(placeObj:any, id: number) {
    return this.http.put(`/api/tours/places/${id}`, placeObj)
  }
  getPlacesList() {
    return this.http.get('/api/tours/places/')
  }
  getPlace(id:number) {
    return this.http.get(`/api/tours/places/${id}`)
  }



  // addPlace(placeObj:any) {
  //   return this.http.post('/api/tours/places/', placeObj)
  // }
  editEnquiry(enquiryObj:any, id: number) {
    return this.http.put(`/api/tours/enquiries/${id}`, enquiryObj)
  }
  getEnquiryList() {
    return this.http.get('/api/tours/enquiries/')
  }
  getEnquiry(id:number) {
    return this.http.get(`/api/tours/enquiries/${id}`)
  }
}
