import { ConstantPool } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';
@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {


    customerReg = new FormGroup({
      customer_id: new FormControl('',Validators.required),
      password: new FormControl('',Validators.required),
      customer_name: new FormControl('',Validators.required),
      phone: new FormControl('',Validators.required),
      email: new FormControl('',Validators.required),
      address: new FormControl('',Validators.required)

    });


  
  cust_data:any=''
  constructor(public service:DataServiceService,private router:Router) { 
    
   }

  ngOnInit(): void {
  }
  
  register(){
    console.log(this.customerReg.getRawValue())

    this.service.registerCustomer(this.customerReg.getRawValue()).subscribe(data=>{if(data!="failed")
    this.router.navigate(["customerLogin"])})
    
    
  }

}
