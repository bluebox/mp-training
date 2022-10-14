import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserserviceService } from '../services/userservice.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-customerpage',
  templateUrl: './customerpage.component.html',
  styleUrls: ['./customerpage.component.css']
})
export class CustomerpageComponent {
emailError:any;
passwordError:any;
loginAndLogOutCheck:Boolean;
token:any;
obj: any;
user: any;

  constructor(private userdata: UserserviceService, private router:Router, private http: HttpClient) {
    if (localStorage.getItem("customer_refresh_token")) {
      console.log(localStorage.getItem("customer_refresh_token"))
       
       this.router.navigate(['/cusdashBoard'])
       this.loginAndLogOutCheck = false
        this.userdata.loginStatus(this.loginAndLogOutCheck)
 
     }
     else{
       this.router.navigate(['/custlogin'])
       this.loginAndLogOutCheck = true
        this.userdata.loginStatus(this.loginAndLogOutCheck)
     }
   }

  ngOnInit(): void {
  }

  customerLoginForm = new FormGroup({
    customer_email: new FormControl('', [Validators.required, Validators.email]),
    customer_password: new FormControl('', [Validators.required, Validators.minLength(4)])
  })
  loginSubmit() {
    // console.log(this.customerLoginForm.value)
    this.userdata.customerlogin(this.customerLoginForm.value).subscribe((response) => {
      // console.log(response)
      let stringif = JSON.stringify(response)
      let parse = JSON.parse(stringif)
      if (parse.customer_refresh_token) {
        localStorage.setItem("customer_refresh_token", parse.customer_refresh_token)

        this.token = this.getUserDetails()
        let userDetails;
        if (this.token) {
          userDetails = this.token.split(".")[1];
          userDetails = window.atob(userDetails);
          userDetails = JSON.parse(userDetails)
          this.obj = {"customer_email":userDetails.emp_id }

          this.http.post("api/user_details/", this.obj).subscribe((res) => {
            let response = JSON.stringify(res)
            let parsed = JSON.parse(response)
            console.log(parsed);
            this.userdata.loginUser.next(parsed)
    
          })
        }
        else {
          // this.loginUser.next(null)
        } 
        this.router.navigate(['/cusdashBoard'])
        // this.userdata.setmsg(this.customerlogOut)
        this.loginAndLogOutCheck = false
        this.userdata.loginStatus(this.loginAndLogOutCheck)

      }
      else{
        this.emailError = parse.emp_email
        this.passwordError = parse.emp_password
      }
    })
  }

  get customer_email() {
    return this.customerLoginForm.get("customer_email")

  }

  get customer_password() {
    return this.customerLoginForm.get("customer_password")

  }
  getUserDetails() {
    return localStorage.getItem("customer_refresh_token")
  
  
  }

}
