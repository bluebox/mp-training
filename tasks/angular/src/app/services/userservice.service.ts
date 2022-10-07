import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

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
  logoutmsg:any;
  loginstatus:any;
  tansactionData:any;
  constructor(private http: HttpClient, private router:Router) {

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
    return this.http.post("/api/customer_loin/", data)

  }
  employeesignin(data:Boolean){
this.logoutmsg = data

  }
  customerlogout() {
    return this.logoutmsg

  }
  getBalance(data:any){
    return this.http.post("api/view_balance/", data)
  }
  moneyTransfer(data:any){
    return this.http.post("api/transfer_money/", data)
  }
loginStatus(data:any){
  this.loginstatus = data

}

getloginStatus(){
  return this.loginstatus
}

gettransactionlist(){
  return this.http.get("api/transaction_list/")
}
sendTransactionData(data:any){
this.tansactionData = data
console.log(this.tansactionData);
this.router.navigate(["../cusdashBoard/transactionlist"])
}
getTransactionData(){
  return this.tansactionData
  
}


}
