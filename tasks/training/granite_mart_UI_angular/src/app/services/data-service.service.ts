import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  private customer = 'http://127.0.0.1:8000/customer/customer/';
  private scustomer = 'http://127.0.0.1:8000/customer/customer';
  private employees = 'http://127.0.0.1:8000/employee/employee';
  private store = 'http://127.0.0.1:8000/item/store';
  private order = 'http://127.0.0.1:8000/order/orders';
  private vehicles = 'http://127.0.0.1:8000/viewVehicles';
  private vehicle = 'http://127.0.0.1:8000/viewVehicle/';
  private item = 'http://127.0.0.1:8000/item/item';

  loged = new Subject<any>()
  constructor(private http: HttpClient) { }
  isAuthenticate: boolean = true
  getCustomers(username: any) {
    return this.http.get(this.customer + username.toString(),{withCredentials:true})
  }

  getEmployees() {
    return this.http.get(this.employees)
  }

  getStores() {
    return this.http.get(this.store)
  }

  getOrders() {
    return this.http.get(this.order)
  }

  getItems(data: any) {
    return this.http.get(this.item)
  }


  getCustomer(customer_id: any) {
    return this.http.get(this.customer + customer_id.toString())
  }

  getEmployee(employee_id: any) {
    return this.http.get(this.employees + '/' + employee_id.toString())
  }

  getStore(store_id: any) {
    return this.http.get(this.store + '/' + store_id.toString())
  }

  getOrder(order_id: any) {
    return this.http.get(this.order + '/' + order_id.toString())
  }

  getItem(item_id: any) {
    return this.http.get(this.item + '/' + item_id.toString())
  }

  loginCustomer(data: any) {
    console.log(data)

    return this.http.post("http://127.0.0.1:8000/logincustomer", data)
  }

  registerCustomer(data: any) {
    console.log(data)

    return this.http.post(this.scustomer, data)
  }

  updateCustomer(data: any, username: any) {
    return this.http.put(this.customer + username.toString(), data)
  }

  deleteCustomer(username: any) {

    return this.http.delete(this.customer + username.toString())
  }

  registerEmployee(data: any) {
    console.log(data)

    return this.http.post(this.employees, data)
  }

  getRoles() {
    return this.http.get("http://127.0.0.1:8000/granite_mart/getRoles")
  }

  deleteEmployee(employee_id: any) {

    return this.http.delete(this.employees + '/' + employee_id.toString())
  }

  updateEmployee(employee_id: any, data: any) {

    return this.http.put(this.employees + employee_id.toString(), data)
  }

  getVehicles() {
    return this.http.get('http://127.0.0.1:8000/vehicle/vehicle')
  }

  getVehicle(vehicle_no: any) {
    return this.http.get('http://127.0.0.1:8000/vehicle/vehicle/' + vehicle_no.toString())
  }


  registerVehicle(data: any) {
    return this.http.post('http://127.0.0.1:8000/registerVehicle', data)
  }

  updateVehicle(data: any, vehicle_no: any) {
    return this.http.post('http://127.0.0.1:8000/updateVehicle/' + vehicle_no.toString(), data)
  }

  deleteVehicle(vehicle_no: any) {
    return this.http.post('http://127.0.0.1:8000/deleteVehicle/' + vehicle_no.toString(), '')
  }

  storeRegistration(data: any) {
    return this.http.post(this.store, data)
  }

  updateStore(store_id: any, data: any) {
    return this.http.post(this.store + store_id.toString(), data)
  }

  authenticate(data: any) {
    return this.http.post("http://127.0.0.1:8000/granite_mart/login", data)
  }

  logout(){
    return this.http.get('http://127.0.0.1:8000/granite_mart/logout')
  }

  getCart(useranme:any){
    return this.http.get('http://127.0.0.1:8000/item/viewCart/'+useranme.toString())
  }

  addToCart(contains_id:any){
    return this.http.post('http://127.0.0.1:8000/item/addToCart',contains_id)
  }

  updateCart(contains_id:any,value:any){
    return this.http.put('http://127.0.0.1:8000/item/addToCart',{'contains_id':contains_id,'value':value})
  }

  getRawItems(){
    return this.http.get('http://127.0.0.1:8000/item/rawItems')
  }
  registerItem(data:any){
    return this.http.post('http://127.0.0.1:8000/item/rawItems',data)
  }

  deleteCart(contains_id:any){
    return this.http.delete('http://127.0.0.1:8000/item/viewCart/'+contains_id)

  }

  createOrder(data:any){
    return this.http.post('http://127.0.0.1:8000/order/orders',data)
  }

  getDelivery(order_id:any){
    return this.http.get('http://127.0.0.1:8000/order/delivery/'+order_id.toString())
  }
}