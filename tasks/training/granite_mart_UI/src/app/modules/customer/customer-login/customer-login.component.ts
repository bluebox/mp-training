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
    username: new FormControl(''),
    password: new FormControl('')
  })
  token_pair:any
  constructor(private service:DataServiceService,private router:Router) { }

  ngOnInit(): void {
  
  }


  login(){
    
    this.service.authenticate(this.customerLogin.getRawValue()).subscribe(result=>{this.token_pair=result;
    console.log(this.token_pair.access)
    if(this.token_pair.access){
      sessionStorage.setItem('token',this.token_pair.access)
      sessionStorage.setItem('refresh',this.token_pair.refresh)
      localStorage.setItem('login','true')
      // this.router.navigate(['adminDashboard'])
    }})
  }

}
