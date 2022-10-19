import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable, throwError } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {
  
  constructor(private http: HttpClient) { }
  getDoctors():Observable <any> {
    return this.http.get("http://127.0.0.1:8000/appointments/get-employee", {
      params: {
        role : "doctor"
      }
    })
  }
  getNurse(): Observable<any> {
    return this.http.get("http://127.0.0.1:8000/appointments/get-employee", {
      params: {
        role: "nurse"
      }
    })
  }
  getLabTechnician(): Observable<any> {
    return this.http.get("http://127.0.0.1:8000/appointments/get-employee", {
      params: {
        role: "lab"
      }
    })
  }
  getSampleCollector(): Observable<any> {
    return this.http.get("http://127.0.0.1:8000/appointments/get-employee", {
      params: {
        role: "sample"
      }
    })
  }

  bookAppointment(data:any){
    return this.http.post<any>("http://127.0.0.1:8000/appointments/book-appointment/",data)
  }
}
