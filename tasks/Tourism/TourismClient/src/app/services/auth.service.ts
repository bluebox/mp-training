import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import { AsyncSubject, BehaviorSubject, map, Observable, Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated: boolean = false;
  isLogin = new BehaviorSubject<boolean>(false);
  user: any;
  // this.isLogin.next(false);


  constructor(private http: HttpClient) {
    let token = this.getCookie('refresh_token')
    console.log('no token');
    if (token && token != "") {
      console.log(token);
      this.user = this.getDecodedAccessToken(token)
      this.isAuthenticated = true
      this.isLogin.next(true)
    }
    // this.isLogin.next(true);
  }

  ngOnInIt() {
    // this.isLogin.subscribe(data => console.log(data))
    // this.isLogin.complete()
  }


  changeAuthenticationToFalse() {
    this.isAuthenticated = false
  }
  changeAuthenticationToTrue() {
    this.isAuthenticated = true
  }
  changeAuthentication() {
    this.isAuthenticated = !this.isAuthenticated
  }

  getCookie(cname:string) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch(Error) {
      return null;
    }
  }




  registerAndGetToken(user: FormGroup) {
    console.log(user);
    let response = this.http.post('/api/bookings/users/', user)
    return response
  }


  logout() {
    return this.http.post('/api/bookings/logout/', {}).pipe(
      map(res => {
        if(res)
          this.isLogin.next(false)
        return res
      })
    )
  }

  loginVerification(credentials: FormGroup) {
    return this.http.post('/api/bookings/login/', credentials).pipe(
      map(res=> {
        if(res){
          this.isLogin.next(true);
        }
        return res;
      })
    )
  }

  getAccessToken() {
    return this.http.post('/api/bookings/login/refresh/', {})
  }

  getUserDetails(token:string) {
    const headers= new HttpHeaders()
    .set('Authorization', 'Token '+ token)
    return this.http.get('/api/bookings/getuser/', {
        headers : headers
      }
    )
  }

  // postFeedback(token:string) {
    // const headers= new HttpHeaders()
    // .set('Authorization', 'Token '+ token)
    // return this.http.post('/api/bookings/postfeedback/', {'comment': 'Tour with this website is awesome', 'rating':5.0},{
    //   headers : headers
    // }
    // )
  // }


  // async loginVerification(credentials: FormGroup) {
  //   let res = await axios.post('/api/bookings/login/', credentials)
  //   console.log(res);
  // }

}
