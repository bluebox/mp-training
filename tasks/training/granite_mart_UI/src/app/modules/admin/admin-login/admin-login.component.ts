import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  adminLogin = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })
  tokenpair:any
  constructor(private service: DataServiceService, private router: Router) { }

  ngOnInit(): void {

  }


  login() {
    console.log(this.adminLogin.getRawValue())
    this.service.authenticate(this.adminLogin.getRawValue()).subscribe(data => {this.tokenpair=data; console.log(data)
    sessionStorage.setItem('token',this.tokenpair.access)
  })
  }


}
