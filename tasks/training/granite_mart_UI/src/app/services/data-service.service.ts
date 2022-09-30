import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  private customers='http://127.0.0.1:8000/viewCustomers';
  private customer='http://127.0.0.1:8000/viewCustomer/';
  private employees='http://127.0.0.1:8000/viewEmployees';
  private employee='http://127.0.0.1:8000/viewEmployee/';
  private stores='http://127.0.0.1:8000/viewStores';
  private store='http://127.0.0.1:8000/viewStore/';
  private orders='http://127.0.0.1:8000/viewOrders';
  private order='http://127.0.0.1:8000/viewOrder/';
  private vehicles='http://127.0.0.1:8000/viewVehicles';
  private vehicle='http://127.0.0.1:8000/viewVehicle/';
  private items='http://127.0.0.1:8000/viewItems';
  private item='http://127.0.0.1:8000/viewItem/';


  constructor(private http:HttpClient) { }

  getCustomers(){
    return this.http.get(this.customers)
  }

  getEmployees(){
    return this.http.get(this.employees)
  }

  getStores(){
    return this.http.get(this.stores)
  }

  getOrders(){
    return this.http.get(this.orders)
  }

  getItems(){
    return this.http.get(this.items)
  }


  getCustomer(customer_id:any){
    return this.http.get(this.customer+customer_id.toString())
  }

  getEmployee(employee_id:any){
    return this.http.get(this.employee+employee_id.toString())
  }

  getStore(store_id:any){
    return this.http.get(this.store+store_id.toString())
  }

  getOrder(order_id:any){
    return this.http.get(this.order+order_id.toString())
  }

  getItem(item_id:any){
    return this.http.get(this.item+item_id.toString())
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
   
    return this.http.post("http://127.0.0.1:8000/registerCustomer",data)
  }

  updateCustomer(data:any,username:any){
    return this.http.post('http://127.0.0.1:8000/updateCustomer/'+username.toString(),data)
  }

  deleteCustomer(username:any){

    return this.http.post('http://127.0.0.1:8000/deleteCustomer/'+username.toString(),'')
  }

  registerEmployee(data:any){
    console.log(data)
   
    return this.http.post("http://127.0.0.1:8000/registerEmployee",data)
  }

  getRoles(){   
    return this.http.get("http://127.0.0.1:8000/getRoles")
  }

  deleteEmployee(employee_id:any){

    return this.http.post('http://127.0.0.1:8000/deleteEmployee/'+employee_id.toString(),'')
  }

  updateEmployee(employee_id:any){

    return this.http.post('http://127.0.0.1:8000/updateEmployee/'+employee_id.toString(),'')
  }

}