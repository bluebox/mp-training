import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ServiceService } from '../service.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  data : string = 'tharun';
  response : any;
  subsc : Subscription = Subscription.EMPTY
  userdata : any ;
  login : any;

  constructor(private sending : Router,private service : ServiceService) {
    this.subsc=this.service.getConfig().subscribe(data => {this.response= data, console.log(this.response)})
   }


  ngOnInit(): void {
    this.userdata = new FormGroup({
      'firstname' : new FormControl ('',[Validators.required,Validators.maxLength(25)]),
      'lastname' : new FormControl('', [Validators.required,Validators.maxLength(25)])
    }),
    this.login = new FormGroup({
      'emailid' : new FormControl ('',[Validators.required,Validators.email]),
      'password' : new FormControl ('',[Validators.required,Validators.maxLength(25)])
    })
  }


 submit (){
   this.sending.navigate(['./login',this.data])
 }
 submitFun(){
  console.log(this.userdata.value);
  this.userdata.reset();
 }
 loginSubmit(){
  console.log(this.login.value)
 }



}
