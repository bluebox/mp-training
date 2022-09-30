import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable, throwError } from 'rxjs'
@Injectable({
  providedIn: 'root'
})
export class AdminSeriveService {

  constructor(private http: HttpClient) {   }

  getAppointments(){
   return this.http.get<any>("http://127.0.0.1:8000/appointments/book-appointment/")
  }
}
