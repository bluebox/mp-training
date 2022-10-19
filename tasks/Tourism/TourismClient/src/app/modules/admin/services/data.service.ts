import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  pageSize: number = 5;


  getPlaces(){
    return this.http.get('/api/tours/places')
  }

  getCoupons(){
    return this.http.get('/api/tours/coupons')

  }
  getVehicles(){
    return this.http.get('/api/tours/vehicles')
  }

  addTour(tourObj:any) {
    return this.http.post('/api/tours/tours_list/', tourObj)
  }

  editTour(tourObj:any, id: number) {
    return this.http.put(`/api/tours/tours_list/${id}`, tourObj)
  }
  deleteTour(id: number) {
    return this.http.delete(`/api/tours/tours_list/${id}`)
  }

  getToursList(page: number) {
    return this.http.get(`/api/tours/tours_list/?page=${page}`)
  }
  getAllToursList() {
    return this.http.get(`/api/tours/all_tours_list/`)
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
  deleteCoupon(id: number) {
    return this.http.delete(`/api/tours/coupons/${id}`)
  }
  getCouponsList(page: number) {
    return this.http.get(`/api/tours/coupons/?page=${page}`)
  }

  getAllCouponsList() {
    return this.http.get('/api/tours/all_coupons/')
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

  deleteVehicle(id:number){
    return this.http.delete(`/api/tours/vehicles/${id}`)
  }

  getAllVehiclesList() {
    return this.http.get('/api/tours/all_vehicles/')
  }
  getVehiclesList(page: number) {
    return this.http.get(`/api/tours/vehicles/?page=${page}`)
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
  deletePlace(id: number) {
    return this.http.delete(`/api/tours/places/${id}`)
  }
  getPlacesList(page: number) {
    return this.http.get(`/api/tours/places/?page=${page}`)
  }
  getAllPlacesList() {
    return this.http.get('/api/tours/all_places/')
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

  // getAllBookingsList(){
  //   return this.http.get('api/bookings/booking/')
  // }

  // cancelBookingByAdmin(id:number){
  //   return this.http.put(`/api/bookings/booking/${id}`, {isCancelled: true})
  // }

  getUsersByAdmin(page: number){
    return this.http.get(`api/bookings/users/?page=${page}`)
  }
  editUsersAdminStatus(id:number){
    return this.http.put(`api/bookings/users/${id}`,{})
  }


  getUserList() {
    return this.http.get('/api/bookings/users/')
  }
  getAllUserList() {
    return this.http.get('/api/bookings/allusers/')
  }

  getUser(id: number){
    return this.http.get(`/api/bookings/users/${id}`)
  }

  editUser(packageObj:any, id: number) {
    return this.http.put(`/api/bookings/users/${id}`, packageObj)
  }
  addUser(packageObj:any) {
    return this.http.post(`/api/bookings/users/`, packageObj)
  }
  deleteUser(id:number) {
    return this.http.delete(`/api/bookings/users/${id}`)
  }


  getBookingList(page: number) {
    return this.http.get(`/api/bookings/admin_booking_list/?page=${page}`)
  }
  getAllBookingList() {
    return this.http.get(`/api/bookings/booking/`)
  }

  getBooking(id: number){
    return this.http.get(`/api/bookings/admin_booking_list/${id}`)
  }

  editBooking(packageObj:any, id: number) {
    return this.http.put(`/api/bookings/admin_booking_list/${id}`, packageObj)
  }
  addBooking(packageObj:any) {
    return this.http.post(`/api/bookings/admin_booking_list/`, packageObj)
  }
  deleteBooking(id:number) {
    return this.http.delete(`/api/bookings/admin_booking_list/${id}`)
  }

  getPaymentList() {
    return this.http.get('/api/bookings/payment/')
  }

  getPayment(id: number){
    return this.http.get(`/api/bookings/payment/${id}`)
  }

  editPayment(packageObj:any, id: number) {
    return this.http.put(`/api/bookings/payment/${id}`, packageObj)
  }
  addPayment(packageObj:any) {
    return this.http.post(`/api/bookings/admin_booking_list/`, packageObj)
  }
  deletePayment(id:number) {
    return this.http.delete(`/api/bookings/admin_booking_list/${id}`)
  }


  getCancellationList(page: number) {
    return this.http.get(`/api/bookings/cancellation/?page=${page}`)
  }

  getCancellation(id: number){
    return this.http.get(`/api/bookings/cancellation/${id}`)
  }

  editCancellation(cancellationObj:any, id: number) {
    return this.http.put(`/api/bookings/cancellation/${id}`, cancellationObj)
  }
  deleteCancellation(id:number) {
    return this.http.get(`/api/bookings/cancellation/${id}`)
  }



  getPackageList(page: number) {
    return this.http.get(`/api/tours/packages/?page=${page}`)
  }
  getAllPackageList() {
    return this.http.get('/api/tours/all_packages/')
  }

  getPackage(id: number){
    return this.http.get(`/api/tours/packages/${id}`)
  }

  editPackage(packageObj:any, id: number) {
    return this.http.put(`/api/tours/packages/${id}`, packageObj)
  }
  addPackage(packageObj:any) {
    return this.http.post(`/api/tours/packages/`, packageObj)
  }
  deletePackage(id:number) {
    return this.http.delete(`/api/tours/packages/${id}`)
  }


  getEmployeeList() {
    return this.http.get('/api/tours/employees/')
  }

  getEmployee(id: number){
    return this.http.get(`/api/tours/employees/${id}`)
  }

  editEmployee(packageObj:any, id: number) {
    return this.http.put(`/api/tours/employees/${id}`, packageObj)
  }
  addEmployee(packageObj:any) {
    return this.http.post(`/api/tours/employees/`, packageObj)
  }
  deleteEmployee(id:number) {
    return this.http.delete(`/api/tours/employees/${id}`)
  }

}
