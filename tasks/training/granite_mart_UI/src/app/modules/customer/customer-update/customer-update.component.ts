import { ConditionalExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { waitForAsync } from '@angular/core/testing';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.css']
})
export class CustomerUpdateComponent implements OnInit {
  username:any
  customer_data:any
  customerUpdate = new FormGroup({
    customer_id: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required),
    customer_name: new FormControl('',Validators.required),
    phone: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required),
    address: new FormControl('',Validators.required)

  });


  constructor(private aroute:ActivatedRoute, private service:DataServiceService,private router:Router) { 

    this.aroute.params.subscribe(data=>{this.username=data['username']})
    this.service.getCustomer(this.username).subscribe(data=>{this.customer_data=data;
      this.customerUpdate.setValue({'customer_id':this.customer_data.username ,
      "password": this.customer_data.password,
      "customer_name": this.customer_data.customer_name,
      "phone": this.customer_data.phone,
      "email": this.customer_data.email,
     "address": this.customer_data.address,
  } )}
      )
    console.log(this.username)
  }

  ngOnInit(): void {
      
  }

  update(){
    console.log(this.customerUpdate.getRawValue())

    this.service.updateCustomer(this.customerUpdate.getRawValue(),this.username).subscribe(data=>{if(data!="failed")
    this.router.navigate(["viewCustomer",this.customerUpdate.getRawValue().customer_id])})
    
    
  }

}
