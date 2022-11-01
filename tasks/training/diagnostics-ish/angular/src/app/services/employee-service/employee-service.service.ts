import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  constructor(private http : HttpClient) { }


  getEmployee(employee_id: string) {
    return this.http.get(`http://127.0.0.1:8000/users/employee/${employee_id}/`)
  }
  deletEemployee(employee_id: string) {
    return this.http.delete(`http://127.0.0.1:8000/users/employee/${employee_id}`)
  }
  updateEmployee(data :any,employee_id: string) {
    return this.http.put(`http://127.0.0.1:8000/users/employee/${employee_id}/`,data)
  }
}
