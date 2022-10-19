import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const baseUrl = 'http://127.0.0.1:8000/';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http : HttpClient) { }

  getCustomerAppointments(cust_id:string){
    return this.http.get<any>(`${baseUrl}appointments/customer-appointments/${cust_id}/`)
  }

  getCustomer(customer_id: string) {
    return this.http.get(`http://127.0.0.1:8000/users/customer/${customer_id}`)
  }
  deleteCustomer(customer_id: string) {
    return this.http.delete(`http://127.0.0.1:8000/users/customer/${customer_id}`)
  }
  updateCustomer(data :any,customer_id: string) {
    return this.http.put(`http://127.0.0.1:8000/users/customer/${customer_id}`,data)
  }
}
