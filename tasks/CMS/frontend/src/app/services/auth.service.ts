import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../modules/admin/reviews';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = "http://10.129.245.173:8989/";

  constructor(private http: HttpClient) { }

  registerUser(data: any) {
    return this.http.post(this.apiUrl + "owner", data);

  }

  registerEmployee(data: {}) {
    return this.http.post(this.apiUrl + "employee", data);

  }

  getOwners() {
    return this.http.get(this.apiUrl + "owner");
  }

  login(data: any) {
    return this.http.post(this.apiUrl + "login", data);
  }

  getreviews(): Observable<any> {
    return this.http.get(this.apiUrl + "reviews");

  }
  updateProfile(data: {}) {
    return this.http.post(this.apiUrl + "updateUser", data);
  }

  addReview(data: {}) {
    return this.http.post(this.apiUrl + "addReview", data);

  }

  showBills(data: {}): Observable<any> {
    return this.http.post(this.apiUrl + "showbills", data);

  }
}
