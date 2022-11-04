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
    username: new FormControl('',Validators.required),
    password: new FormControl('',Validators.minLength(3))
  })
  token_pair:any
  username:any
  constructor(private service:DataServiceService,private router:Router) { }

  ngOnInit(): void {
  
  }


  login(){
    if(this.customerLogin.valid){
      this.username=(this.customerLogin.getRawValue()['username'])
    this.service.authenticate(this.customerLogin.getRawValue()).subscribe(result=>{this.token_pair=result;
    console.log(result)
    if(result!='failed'){
      this.router.navigate(['viewCustomer',this.username.toString()])
    }})
    }
    else
    alert('enter correct details')
    
  }
  get user(){
    return this.customerLogin.get('username')
  }

}
