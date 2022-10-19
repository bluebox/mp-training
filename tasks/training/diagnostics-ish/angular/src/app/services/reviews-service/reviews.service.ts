import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root',
})
export class ReviewsService {
  constructor(private http: HttpClient) {}

  // Review Services

  getReviews(): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/review/'}`);
  }

  getReview(id: any): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/review/'}`, id);
  }

  setReview(data: any) {
    return this.http.put<any>(`${baseUrl}${'appointments/review/'}`, data);
  }

  updateReview(data: any) {
    return this.http.put<any>(`${baseUrl}${'appointments/review/'}`, data);
  }

  deleteReview(data: any) {
    return this.http.delete<any>(`${baseUrl}${'appointments/review/'}`, data);
  }
}
