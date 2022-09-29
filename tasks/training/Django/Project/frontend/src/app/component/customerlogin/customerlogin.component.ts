import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { GeneralService } from 'src/app/general.service';
import { Subscription } from 'rxjs';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customerlogin',
  templateUrl: './customerlogin.component.html',
  styleUrls: ['./customerlogin.component.css']
})
export class CustomerloginComponent implements OnInit {

  msg: any
  login! : FormGroup
  constructor( private service: GeneralService, private route: Router) { }

  ngOnInit(): void {
    this.login= new FormGroup({
      email : new FormControl('' ),
      password : new FormControl('')

    })

  }
  customerLogin(){
    console.log('here')
    this.service.customerLogin(this.login.value).subscribe((data : any) =>{(this.msg=data)
      window.sessionStorage.setItem('customer_id',JSON.stringify(data));this.route.navigate(['available-vehicles'])}
      , (err) => {alert('Invalid credentials')},  )}
}



