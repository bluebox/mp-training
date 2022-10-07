import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {Router} from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-createcustomeraccount',
  templateUrl: './createcustomeraccount.component.html',
  styleUrls: ['./createcustomeraccount.component.css']
})
export class CreatecustomeraccountComponent {
status:any;
accountstatus:any;
  constructor(private router: Router, private http:HttpClient, private userdata:UserserviceService) { 
    if (localStorage.getItem("refresh_token")){
        this.router.navigate(['/empdashBoard/createCusAcc']);
      }
      else{
        this.router.navigate(['/home']);
      }
  }

  ngOnInit(): void {
  }
  

  CustomerAccountCreate = new FormGroup({
    customer_name :new FormControl(),
    phone_number: new FormControl(),
    customer_email: new FormControl(), 
    customer_password : new FormControl(), 
    customer_address :new FormControl()
  })
  accountCreate = new FormGroup({
    account_number :new FormControl(),
    amount: new FormControl(),
    account_type: new FormControl(), 
    customer : new FormControl()
  })

  createAccount(){
    this.userdata.createaccount(this.CustomerAccountCreate.value).subscribe((response) =>{
      console.log(typeof(response))
      this.status = response
    })
  }

  addingAccount(){
    console.log(this.accountCreate.value);
    this.userdata.addaccount(this.accountCreate.value).subscribe((response) =>{
      console.log(response)
      this.accountstatus = response
    
    })
      
    
  }
}
