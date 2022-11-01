import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {BehaviorSubject} from 'rxjs'
@Injectable({
  providedIn: 'root'
})

export class SubjectServiceService  {
 
  loggedIn : boolean = false
  userType :string =''
  isCustomer : boolean = false
  
  public usernameSubject = new BehaviorSubject("");
  public isLoggedInSubject =  new BehaviorSubject(false)
  public userTypeSubject = new BehaviorSubject("")
  public userTypeIdSubject = new BehaviorSubject("")

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
    this.http.get("http://127.0.0.1:8000/users/user/").subscribe({
      next: (data: any) => {
        this.usernameSubject.next(data['username'])
        this.isLoggedInSubject.next(true)
        this.userTypeSubject.next(data['user_type'])
        this.userTypeIdSubject.next(data['user_type_id'])
        this.loggedIn = true
        this.userType = data['user_type']

      //  ccccccccc
        if (data['user_type']){
          this.isCustomer  = true
        }
        window.localStorage.setItem("customerId", JSON.stringify(data['user_type_id']))
      },
      error: () => {
        this.usernameSubject.next("")
        this.isLoggedInSubject.next(false)
        this.userTypeSubject.next("")
        this.userTypeIdSubject.next("")
        this.loggedIn = false
        this.isCustomer = false
        this.userType = ""
      }
    })
   }
  sendLoginDetails(data : any){
    this.usernameSubject.next(data['username'])
    this.isLoggedInSubject.next(true)
    this.userTypeSubject.next(data['user_type'])
    this.userTypeIdSubject.next(data['user_type_id'])
    this.loggedIn = true
    this.userType = ""
    if (data['user_type']) {
      this.isCustomer = true
    }
  }


  logoutService(){
    this.usernameSubject.next("")
    this.isLoggedInSubject.next(false)
    this.userTypeSubject.next("")
    this.userTypeIdSubject.next("")
    this.loggedIn = false
    this.isCustomer = false
  }

}