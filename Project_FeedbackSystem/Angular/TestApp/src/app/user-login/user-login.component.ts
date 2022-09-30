import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  loginform = new FormGroup({
    username : new FormControl('', [Validators.required, Validators.email]),
    password : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z0-9]+$'), Validators.minLength(6)])
  }
  )
  get username(){
    return this.loginform.get("username");
  }
  get password(){
    return this.loginform.get("password");
  }
  proceed(){
    console.warn(this.loginform.value)
  }
  constructor() { }

  ngOnInit(): void {
  }
  registerNewUser(){
    alert("Register Successfull")
  }
}
