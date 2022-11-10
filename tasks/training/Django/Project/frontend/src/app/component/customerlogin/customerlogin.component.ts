import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { GeneralService } from 'src/app/general.service';
import { Subscription } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customerlogin',
  templateUrl: './customerlogin.component.html',
  styleUrls: ['./customerlogin.component.css']
})
export class CustomerloginComponent implements OnInit {

  msg: any
  submit!: boolean;

  constructor( private service: GeneralService, private route: Router) {

    if (window.sessionStorage.getItem('token')){
      route.navigate(['available-vehicles'])
    }
  }

  ngOnInit(): void {

  }
  login: FormGroup= new FormGroup({
    email : new FormControl('', [Validators.required, Validators.email]),
    password : new FormControl('', [Validators.required])

  })

  get formData() {
    return this.login.controls
  }

  customerLogin(){
    console.log('here')
    if(this.login.valid){
      this.service.customerLogin(this.login.value).subscribe((data : any) =>{(this.msg=data)
        window.sessionStorage.setItem('token',JSON.stringify(this.msg)),
        this.route.navigate(['available-vehicles'])},
        (err) => {alert('Invalid credentials')},  )
      }
    }
}



