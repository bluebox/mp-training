import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FacultySignupComponent } from '../faculty-signup/faculty-signup.component';
import { SignupComponent } from '../signup/signup.component';

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
  usersList: Users[] = [
    {users : 'Student', userValue: 'student'},
    {users : 'Faculty', userValue: 'faculty'},
  ];

  constructor(
    private dialog : MatDialog,
    private signupDialog: MatDialog,
    private signinDialog: MatDialog
    ){ }

  openStudentSignup() {
    this.signupDialog.open(SignupComponent, {
      width: '40%',
      height: '70%',
    });
  }

  openFacultySignup() {
    this.signupDialog.open(FacultySignupComponent, {
      width: '40%',
      height: '70%',
    });
  }

  openSigninDialog() {
    this.signinDialog.open(UserLoginComponent, {
      width: '40%',
      height: '70%',
    });
  }

  get username(){
    return this.loginform.get("username");
  }
  get password(){
    return this.loginform.get("password");
  }
  proceed(){
    console.warn(this.loginform.value)
  }

  ngOnInit(): void {
  }
  loginProcess(){
    alert("Login Successfull")
  }


}

interface Users {
  users:string;
  userValue:string
}
