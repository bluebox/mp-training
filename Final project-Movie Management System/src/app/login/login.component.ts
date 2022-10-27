import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserdataService } from '../services/userdata.service';
import { SignupComponent } from '../signup/signup.component';

FormControl
@Component({
  selector: 'app-login',
  template: `
  <h1>Login Here</h1>
  <form [formGroup]="Login" (ngSubmit)="OnSubmit()" >
      <div class="form-group" style="margin-bottom:20px">
          <label>Email</label>
          <input type="email" class="form-control" formControlName="User_email" id="email" name="email" required>
          <mat-error *ngIf="Login.get('User_email')?.touched && Login.get('User_email')?.hasError('required')">Enter your email</mat-error>
      </div>
      <div class="form-group">
          <label>Password</label>
          <input type="password" class="form-control" formControlName="password" name="password" required>
          <mat-error *ngIf="Login.get('password')?.touched && Login.get('password')?.hasError('required')">Enter your password</mat-error>

      </div>
      <br>
          <button mat-raised-button  color="primary" type="submit" >Login</button>
      <p>New User?</p>
      <p style="cursor:pointer" (click)=openSignup()>Register Here</p>
  </form>
  `,
  styles: [
  ]
})
export class LoginComponent implements OnInit {
  Login:FormGroup=new FormGroup({
    User_email:new FormControl("",Validators.required),
    password:new FormControl("",Validators.required)
  })
  public userdata:any
  public token!:any
  public Singleuser!:any
  

  constructor(public dialog:MatDialog,private user:UserdataService,private router:Router) { }

  ngOnInit(): void {
  }
  OnSubmit(){
    if(this.Login.valid){
    let guest=JSON.stringify(this.Login.value)
    this.user.userLogin(this.Login.value).subscribe((data)=>{this.token=data,console.log(guest,this.token.message);
      if(this.token.message==='sucess'){
        this.user.setstatus(true)
        console.log(this.user.status)
      }
      else{
        this.user.setstatus(false)
      }
    this.user.getuser().subscribe(data=>{this.userdata=data;
      this.user.setUser(this.userdata)
    if(this.userdata.Role==='User'){
      this.router.navigate([""])
    }
    else{
      this.user.setrole(this.userdata.role)
      this.router.navigate(["admin"])
      this.user.setrole(this.userdata.Role)
      console.log(this.user.role)
    }
  })})
    this.dialog.closeAll()}
    else{
      alert("invalid credentials")
      console.log("invalid")
    }
   
  }
  openSignup(){
    this.dialog.closeAll()
    this.dialog.open(SignupComponent)
  }


}
