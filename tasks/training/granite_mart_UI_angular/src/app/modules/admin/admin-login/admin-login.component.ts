import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  adminLogin = new FormGroup({
    username: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  })
  tokenpair:any
  constructor(private service: DataServiceService, private router: Router, private cservice:CookieService) { }

  ngOnInit(): void {

  }


  login() {
      if(this.adminLogin.valid){
        console.log(this.adminLogin.getRawValue())
        
        this.service.authenticate(this.adminLogin.getRawValue()).subscribe(data => {this.tokenpair=data; console.log(this.cservice.get('isAdmin')=='True' && this.cservice.get('login')=='true')
        if(this.cservice.get('isAdmin')=='True' && this.cservice.get('login')=='true')
        this.router.navigate(['customersList',this.adminLogin.getRawValue()['username']])
      })
      }
  }


}
