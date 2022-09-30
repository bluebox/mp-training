import { Component, OnInit } from '@angular/core';
import { FormControl,FormGroup, Validators, ɵNgSelectMultipleOption } from '@angular/forms';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css'],
})
export class CustomerLoginComponent implements OnInit {
  customerLogin = new FormGroup({
    customer_id: new FormControl(''),
    password: new FormControl('')
  })
  
  login_value:any=''
  constructor(private service:DataServiceService,private router:Router) { }

  ngOnInit(): void {
  
  }


  login(){
    console.log(this.customerLogin.getRawValue())
    this.service.loginCustomer(this.customerLogin.getRawValue()).subscribe(data=>{this.router.navigate(['viewCustomer',data])})
    
  }

}
