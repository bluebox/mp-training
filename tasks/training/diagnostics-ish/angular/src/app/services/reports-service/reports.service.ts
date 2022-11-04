import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root',
})
export class ReportsService {
  constructor(private http: HttpClient) {}

  // Report Services

  getReports(): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/report/'}`);
  }

  getReport(id: any): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/report/'}`, id);
  }

  setReport(data: any) {
    return this.http.put<any>(`${baseUrl}${'appointments/report/'}`, data);
  }

  updateReport(data: any) {
    return this.http.put<any>(`${baseUrl}${'appointments/report/'}`, data);
  }

  deleteReport(data: any) {
    return this.http.delete<any>(`${baseUrl}${'appointments/report/'}`, data);
  }
}
