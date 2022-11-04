import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { map, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
  // url = 'http://localhost:8000/class_apiview/'
  // url1 = "http://localhost:8000/emp_apiview/"
  // url2 = "http://localhost:8000/emp_login/"
  // url3 = "http://localhost:8000/acc_create/"
  // url4 = "http://localhost:8000/adding_account/"
  // url5 = "http://localhost:8000/customer_loin/"
  // url6 = "http://127.0.0.1:8000/view_balance/"
  // url7 = "http://127.0.0.1:8000/transfer_money/"

  msg: any;
  logoutmsg: any;
  loginstatus: any;
  tansactionData: any;

  data: any;
  parsedData: any;
  Balance: any;
  token: any;
  obj: any;
  user: any;
  loginUser = new Subject<any>()

  constructor(private http: HttpClient, private router: Router) {
  
  }

  userapi() {
    return this.http.get("api/class_apiview/")
  }

  empapi(data: any) {
    return this.http.post("api/emp_apiview/", data)
  }
  emploginapi(data: any) {


    return this.http.post("api/emp_login/", data)

  }
  setmsg(data: Boolean) {
    this.msg = data
    console.log("servies", this.msg);

  }
  getmsg() {
    return this.msg
  }
  createaccount(data: any) {
    return this.http.post("api/acc_create/", data)
  }
  addaccount(data: any) {
    return this.http.post("api/adding_account/", data)
  }

  customerlogin(data: any) {
    return this.http.post("/api/customer_login/", data)
    
    // .pipe(
    //   map(res => {
    //     console.log(res);
    //     let response = JSON.stringify(res)
    //     let parsed = JSON.parse(response)
    //     console.log(parsed.customer_data);
    //     this.loginUser.next(parsed.customer_data)
        
    //     return res
    //   })
    // )

  }
  userDetails(){
    this.token = this.getUserDetails()
    let userDetails;
    if (this.token) {
      userDetails = this.token.split(".")[1];
      userDetails = window.atob(userDetails);
      userDetails = JSON.parse(userDetails)
      this.obj = {"email": userDetails.emp_id}
      console.log(this.obj);
      return this.obj
    } 
    else {
     
    }
  }
  employeeDetails(){
    this.token = this.getemployeeEmail()
    let userDetails;
    if (this.token) {
      userDetails = this.token.split(".")[1];
      userDetails = window.atob(userDetails);
      userDetails = JSON.parse(userDetails)
      this.obj = {"email": userDetails.emp_id}
      return this.obj
    } 
    else {
     
    }
  }
  getUserDetails(){
    return localStorage.getItem("customer_refresh_token")


  }
  
  employeesignin(data: Boolean) {
    this.logoutmsg = data

  }
  customerlogout() {
    return this.logoutmsg

  }
  getBalance(data: any) {
    return this.http.post("api/view_balance/", data)
  }
  moneyTransfer(data: any) {
    return this.http.post("api/transfer_money/", data)
  }
  loginStatus(data: any) {
    this.loginstatus = data

  }

  getloginStatus() {
    return this.loginstatus
  }

  gettransactionlist(data:any) {
    return this.http.post("api/transaction_list/", data)
  }
  getemployeeEmail(){
    return localStorage.getItem("refresh_token")
  }


getDetails():Observable<string>{
  return this.loginUser.asObservable()
}
IsLogin(){
  return localStorage.getItem("customer_refresh_token")
}
 
}
