import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-createcustomeraccount',
  templateUrl: './createcustomeraccount.component.html',
  styleUrls: ['./createcustomeraccount.component.css']
})
export class CreatecustomeraccountComponent {
  status: any;
  accountstatus: any;
  constructor(private router: Router, private http: HttpClient, private userdata: UserserviceService) {
    if (localStorage.getItem("refresh_token")) {
      this.router.navigate(['/empdashBoard/createCusAcc']);
    }
    else {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {

  }


  CustomerAccountCreate = new FormGroup({
    customer_name: new FormControl(),
    phone_number: new FormControl(),
    customer_email: new FormControl('',[
      Validators.email, Validators.email,
   
  Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
    customer_password: new FormControl('', [Validators.required, Validators.minLength(4)]),
    customer_address: new FormControl(),
    avg_monthly_salary: new FormControl(),
    employee_type: new FormControl()
  })

  accountCreate = new FormGroup({
    account_number: new FormControl(),
    amount: new FormControl(),
    account_type: new FormControl(),
    customer: new FormControl()
  })

  createAccount() {
    this.userdata.createaccount(this.CustomerAccountCreate.value).subscribe((response) => {
      console.log(this.CustomerAccountCreate.value)
      this.status = response
    })

  }

  addingAccount() {
    console.log(this.accountCreate.value);
    this.userdata.addaccount(this.accountCreate.value).subscribe((response) => {
      console.log(response)
      this.accountstatus = response

    })


  }
  get customer_name(){
    return this.CustomerAccountCreate.get("customer_name")
  }
  get phone_number(){
    return this.CustomerAccountCreate.get("phone_number")
  }
  get customer_email(){
    return this.CustomerAccountCreate.get("customer_email")
  }
  get customer_password(){
    return this.CustomerAccountCreate.get("customer_password")
  }
  get customer_address(){
    return this.CustomerAccountCreate.get("customer_address")
  }
  get avg_monthly_salary(){
    return this.CustomerAccountCreate.get("avg_monthly_salary")
  }
  get employee_type(){
    return this.CustomerAccountCreate.get("employee_type")
  }


}
