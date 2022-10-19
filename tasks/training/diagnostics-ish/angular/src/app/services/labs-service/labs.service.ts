import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root',
})
export class LabsService {
  constructor(private http: HttpClient) {}

  // Labs
  createLab(data:any){
    return this.http.post<any>(`${baseUrl}${'appointments/labs/'}`, data);
  }
  getLabs(): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/labs/'}`);
  }

  // lab 
  getLab(id: any): Observable<Object> {
    return this.http.get<any>(`${baseUrl}appointments/lab/${id}`);
  }

  updateLab(id:any,data: any) {
    return this.http.put<any>(`${baseUrl}appointments/lab/${id}}`, data);
  }

  deleteLab(id: any) {
    return this.http.delete<any>(`${baseUrl}appointments/lab/${id}`);
  }
}
