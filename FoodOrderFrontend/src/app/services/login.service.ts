import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private urlCustomer:string="http://127.0.0.1:8000/login/"
  private urlUser:string="http://127.0.0.1:8000/user/"
  private logoutUrl:string="http://127.0.0.1:8000/logout/"
  constructor(private http:HttpClient) { }


  postUser(data:object)
  {
    return this.http.post<any>(this.urlCustomer,data,{ observe: 'response', withCredentials: true })
  }
  loginCheck(){
    return this.http.get<any>(this.urlUser,{ observe: 'response', withCredentials: true })
  }
  logout(){
    return this.http.get<any>(this.logoutUrl,{ observe: 'response', withCredentials: true })
  }
}
