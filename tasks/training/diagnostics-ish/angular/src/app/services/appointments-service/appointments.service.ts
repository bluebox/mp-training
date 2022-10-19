import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root',
})
export class AppointmentsService {
  constructor(private http: HttpClient) {}

  // Appointments Services.

  getAppointments(): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/book-appointment/'}`);
  }
  getSearchedAppointments(text:any): Observable<Object> {
    let queryParams = { "text": text };
    return this.http.get<any>(`${baseUrl}${'appointments/search-appointment/'}`, { params: queryParams });
  }
  setAppointment(data: any) {
    return this.http.post<any>(`${baseUrl}${'appointments/book-appointment/'}`, data);
  }

  getAppointment(id: any): Observable<Object> {
    return this.http.get<any>(`${baseUrl}appointments/appointment/${id}/ `);
  }

  updateAppointment(id :any , data: any) {
    return this.http.put<any>(`${baseUrl}appointments/appointment/${id}/`, data);
  }

  deleteAppointment(id: any) {
    return this.http.delete<any>(
      `${baseUrl}appointments/appointment/${id}/`
    );
  }
}
