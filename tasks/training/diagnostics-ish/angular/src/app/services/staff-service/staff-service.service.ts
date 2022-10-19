import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root'
})
export class StaffServiceService {

  constructor(private http:HttpClient) { }

  getEmployees(){
    return this.http.get<any>(`${baseUrl}users/register-employee/`)
  }

}
