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

  isAuthenticated = new Subject<boolean>();

  isLogin!: boolean;
  currentLoginUser = new Subject<any>();
  currentUser: any;
  // this.isLogin.next(false);


  constructor(private http: HttpClient) {
    console.log("constructor");
    this.getUserDetails().subscribe(
      user => {
        let userString = JSON.stringify(user)
        let userObj = JSON.parse(userString)
        // console.log(userObj);
        this.currentUser = userObj
        this.currentLoginUser.next(userObj)
        this.isAuthenticated.next(true);
        this.isLogin = true;
      },
      err => {
        console.log(err.error.detail);
        this.isAuthenticated.next(false)
        this.currentUser = null;
        this.currentLoginUser.next(null)
        this.isLogin = false
      }
    )
  }

  checkIfUserAuthenticated(): Promise<boolean> {
    return new Promise((resolve)=> {
      this.isAuthenticated.subscribe(res=>{
        if(res){
          console.log("guard subscribe true");
          resolve(true)
        }
        else{
          resolve(false)
        }
      }, err => {resolve(false)})
    })

  }

  editUserProfile(userObj:any){
    return this.http.put('/api/bookings/user/', userObj).pipe(
      map(user => {
        this.currentLoginUser.next(user);
        this.currentUser = user;
        return user
      })
    )
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
    return this.http.post('/api/bookings/users/', user).pipe(
      map(res => {
        this.isLogin = true
        this.isAuthenticated.next(true)
        let userString = JSON.stringify(res)
        let userObj = JSON.parse(userString)
        let user = userObj.substring(1, userObj.length-1);
        let userObject = JSON.parse(user)
        this.currentUser = userObject.fields
        this.currentLoginUser.next(userObject.fields)
        return userObject.fields;
      })
    )
  }


  logout() {
    return this.http.post('/api/bookings/logout/', {}).pipe(
      map(res => {
        if(res)
          this.isAuthenticated.next(false)
          this.isLogin = false
          this.currentUser = null;
          this.currentLoginUser.next(null)
        return res
      })
    )
  }

  loginVerification(credentials: FormGroup) {
    return this.http.post('/api/bookings/login/', credentials).pipe(
      map(res=> {
        this.isAuthenticated.next(true);
        this.isLogin = true
        let userString = JSON.stringify(res)
        let userObj = JSON.parse(userString)
        let user = userObj.substring(1, userObj.length-1);
        let userObject = JSON.parse(user)
        this.currentUser = userObject.fields
        this.currentLoginUser.next(userObject.fields)
        return userObject.fields;
      })
    )
  }

  // getAccessToken() {
  //   return this.http.post('/api/bookings/login/refresh/', {})
  // }

  getUserDetails() {
    return this.http.get('/api/bookings/user/')
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
