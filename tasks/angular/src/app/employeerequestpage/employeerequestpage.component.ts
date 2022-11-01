import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-employeerequestpage',
  templateUrl: './employeerequestpage.component.html',
  styleUrls: ['./employeerequestpage.component.css']
})
export class EmployeerequestpageComponent {
  loginAndLogOutCheck: any
  email: any;
  obj: any;
  object:any
  requestdata: any
  status: any;
  emailObject:any;
  account_id:any;
  res:any
  constructor(private router: Router, private userdata: UserserviceService, private http: HttpClient) {
    if (localStorage.getItem("refresh_token")) {
      this.router.navigate(['/empdashBoard/employeerequest']);
      this.loginAndLogOutCheck = false
      this.userdata.loginStatus(this.loginAndLogOutCheck)
      this.email = this.userdata.employeeDetails()
      this.http.post("api/emp_request_data/", this.email).subscribe(data => {
        this.requestdata = data

      })
    }
    else {
      this.router.navigate(['/home']);
    }
  }


  ngOnInit(): void {
  }
  creditsendemail(email: any) {

    this.router.navigate(["/empdashBoard/employeerequest/creditcard", email])


  }
  debitsendemail(email: any) {
    this.router.navigate(["/empdashBoard/employeerequest/debitcard", email])

  }
  EligibiltyCheck(email: any){
    this.object = {"email":email}
    console.log(this.object);
    this.http.post("api/loan_eligibility/", this.object).subscribe(res =>{
      console.log(res);
      this.res = res
    })


  }
  ChangePhoneNumber(email:any){
    this.router.navigate(["/empdashBoard/employeerequest/changePhoneNumber", email])

  }
  ChangeEmail(email:any){

    this.router.navigate(["/empdashBoard/employeerequest/changeEmail", email])
  }

}
