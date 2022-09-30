import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Observable,throwError } from 'rxjs'
import {map, retry, catchError } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) { }

  registerCustomer(data: any){
    return this.http.post<any>('http://127.0.0.1:8000/users/register-customer/',data)
  }
  registerEmployee(data:any){
    return this.http.post<any>('http://127.0.0.1:8000/users/register-employee/', data)

  }
  getUsers(){
    return this.http.get('http://127.0.0.1:8000/users/register-customer/')
  }
  getBranches(){
    return this.http.get('http://127.0.0.1:8000/users/branch/')

  }

  handleError(err:any):any{
      // console.log(err);  
      let errorMessage =""
      if(err.error instanceof ErrorEvent){
        errorMessage = `Error: ${err.message}`;
      }else{
        errorMessage = `Error Code : ${err.status}\nMessage: ${err.message}`;
      }
      alert(errorMessage);
      return throwError(errorMessage)
  }
  loginUser(data:any): Observable<any>{
    return this.http.post<any>('http://127.0.0.1:8000/users/login/',data).pipe(
      retry(3),
      catchError(this.handleError)
    )
  }
  logoutUser(data:any){
    return this.http.post("http://localhost:8000/users/logout/",data)
  }
  public saveData(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  public getData(key: string) {
    return localStorage.getItem(key)
  }
  public removeData(key: string) {
    localStorage.removeItem(key);
  }

  public clearData() {
    localStorage.clear();
  }

  }
