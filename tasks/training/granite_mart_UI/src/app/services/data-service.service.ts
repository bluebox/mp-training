import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  private customer='http://127.0.0.1:8000/customer/customer';
  private employees='http://127.0.0.1:8000/employee/employee';
  private store='http://127.0.0.1:8000/item/store';
  private order='http://127.0.0.1:8000/order/order';
  private vehicles='http://127.0.0.1:8000/viewVehicles';
  private vehicle='http://127.0.0.1:8000/viewVehicle/';
  private item='http://127.0.0.1:8000/item/item';


  constructor(private http:HttpClient) { }

  getCustomers(){
    return this.http.get(this.customer+'s')
  }

  getEmployees(){
    return this.http.get(this.employees)
  }

  getStores(){
    return this.http.get(this.store)
  }

  getOrders(){
    return this.http.get(this.order)
  }

  getItems(data:any){
    return this.http.get(this.item)
  }


  getCustomer(customer_id:any){
    return this.http.get(this.customer+'/'+customer_id.toString())
  }

  getEmployee(employee_id:any){
    return this.http.get(this.employees+'/'+employee_id.toString())
  }

  getStore(store_id:any){
    return this.http.get(this.store+'/'+store_id.toString())
  }

  getOrder(order_id:any){
    return this.http.get(this.order+'/'+order_id.toString())
  }

  getItem(item_id:any){
    return this.http.get(this.item+'/'+item_id.toString())
  }

  loginCustomer(data:any){
    console.log(data)
   
    return this.http.post("http://127.0.0.1:8000/logincustomer",data)
  }


  logOut(){
    console.log()
 
    return this.http.get("http://127.0.0.1:8000/logout")
  }


  registerCustomer(data:any){
    console.log(data)
   
    return this.http.post(this.customer,data)
  }

  updateCustomer(data:any,username:any){
    return this.http.put(this.customer+'/'+username.toString(),data)
  }

  deleteCustomer(username:any){

    return this.http.delete(this.customer+'/'+username.toString())
  }

  registerEmployee(data:any){
    console.log(data)
   
    return this.http.post(this.employees,data)
  }

  getRoles(){   
    return this.http.get("http://127.0.0.1:8000/granite_mart/getRoles")
  }

  deleteEmployee(employee_id:any){

    return this.http.delete(this.employees+'/'+employee_id.toString())
  }

  updateEmployee(employee_id:any,data:any){

    return this.http.put(this.employees+'/'+employee_id.toString(),data)
  }

  getVehicles(){
    return this.http.get('http://127.0.0.1:8000/viewVehicles')
  }

  getVehicle(vehicle_no:any){
    return this.http.get('http://127.0.0.1:8000/viewVehicle/'+vehicle_no.toString())
  }


  registerVehicle(data:any){
    return this.http.post('http://127.0.0.1:8000/registerVehicle',data)
  }

  updateVehicle(data:any,vehicle_no:any){
      return this.http.post('http://127.0.0.1:8000/updateVehicle/'+vehicle_no.toString(),data)
  }

  deleteVehicle(vehicle_no:any){
    return this.http.post('http://127.0.0.1:8000/deleteVehicle/'+vehicle_no.toString(),'')
  }

  storeRegistration(data:any){
    return this.http.post(this.store,data)
  }
  
  updateStore(store_id:any,data:any){
    return this.http.post(this.store+store_id.toString(),data)
  }

  authenticate(data:any){
    return this.http.post("http://127.0.0.1:8000/granite_mart/api/token/",data)
  }

}