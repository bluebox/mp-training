import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../services/userservice.service';
import { Router } from "@angular/router"
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-customerbalacecheck',
  templateUrl: './customerbalacecheck.component.html',
  styleUrls: ['./customerbalacecheck.component.css']
})
export class CustomerbalacecheckComponent {
  data: any;
  parsedData: any;
  Balance: any;
  token: any;
  obj:any;
  userBalance:any;
  constructor(private router: Router, private http: HttpClient, private userdata:UserserviceService) {
    if (localStorage.getItem("customer_refresh_token")) {
      this.router.navigate(['/cusdashBoard/cusbalcheck']);
    }
    else {
      this.router.navigate(['/home']);
    }

  }

  ngOnInit(): void {}

  balanceForm = new FormGroup({
    account_type: new FormControl(''),
    // account_number: new FormControl('')

  })

  getAccountBalance(){
    this.token = this.getUserDetails()
    let userDetails;
    if (this.token) {
      userDetails = this.token.split(".")[1];
      userDetails = window.atob(userDetails);
      userDetails = JSON.parse(userDetails)
      this.obj = {"account_type":this.balanceForm.value.account_type, "email": userDetails.emp_id}
    } 
    else {
     
    }

    this.userdata.getBalance(this.obj).subscribe((res)=>{

     this.userBalance = res
    })
    

  }

  getUserDetails(){
    return localStorage.getItem("customer_refresh_token")


  }

}
