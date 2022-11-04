import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';

const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  constructor(private http: HttpClient) {}


 
  getDetailsForAppointmentBooking(): Observable<Object> {
    return this.http.get<any>(`${baseUrl}${'appointments/get-details-for-booking-appointment/'}`);
  }
  registerCustomer(data: any) {
    return this.http.post<any>(
      'http://127.0.0.1:8000/users/register-customer/',
      data
    );
  }
  registerEmployee(data: any) {
    return this.http.post<any>(
      'http://127.0.0.1:8000/users/register-employee/',
      data
    );
  }
  getCustomers() {
    return this.http.get('http://127.0.0.1:8000/users/register-customer/');
  }
  getSearchedCustomers(text : string) {
    let queryParams = { "text": text };
    return this.http.get(`${baseUrl}users/filter_customer/`, { params: queryParams });
  }
  getCustomer(customer_id:string){
    return this.http.get(`http://127.0.0.1:8000/users/customer/${customer_id}`)
  }
  updateCustomer(customer_id:string , data :any){
    return this.http.put(`${baseUrl}users/customer/${customer_id}/`,data)
  }

  getBranches() {
    return this.http.get('http://127.0.0.1:8000/users/branch/');
  }

  handleError(err: any): any {
    // console.log(err);
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = `Error: ${err.message}`;
    } else {
      errorMessage = `Error Code : ${err.status}\nMessage: ${err.message}`;
    }
    alert(errorMessage);
    return throwError(errorMessage);
  }
  loginUser(data: any): Observable<any> {
    return this.http
      .post<any>('http://127.0.0.1:8000/users/login/', data)
      .pipe(retry(3), catchError(this.handleError));
  }
  logoutUser(data: any) {
    return this.http.post('http://localhost:8000/users/logout/', data);
  }
  public saveData(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  public getData(key: string) {
    return localStorage.getItem(key);
  }
  public removeData(key: string) {
    localStorage.removeItem(key);
  }

  public clearData() {
    localStorage.clear();
  }
}
