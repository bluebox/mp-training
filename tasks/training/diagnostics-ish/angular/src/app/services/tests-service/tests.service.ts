import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root',
})
export class TestsService {
  constructor(private http: HttpClient) {}

  // Test Services

  addTest(data:any){
    return this.http.post<any>(`${baseUrl}${'appointments/tests/'}`,data);
  }

  getTests(): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/tests/'}`);
  }

  getTest(id: any): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/tests/'}`, id);
  }

  setTest(data: any) {
    return this.http.put<any>(`${baseUrl}${'appointments/tests/'}`, data);
  }

  updateTest(data: any) {
    return this.http.put<any>(`${baseUrl}${'appointments/tests/'}`, data);
  }

  deleteTest(data: any) {
    return this.http.delete<any>(`${baseUrl}${'appointments/tests/'}`, data);
  }
}
