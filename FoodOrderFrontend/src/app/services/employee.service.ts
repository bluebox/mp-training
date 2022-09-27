import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../interfaces/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private url:string="http://127.0.0.1:8000/employee/"

  constructor(private http:HttpClient) { }


  getEmp():Observable<Employee[]>{
    return this.http.get<Employee[]>(this.url)
  }


  postEmp(data:object)
  {
    return this.http.post<Employee>(this.url,data)
  }
}
