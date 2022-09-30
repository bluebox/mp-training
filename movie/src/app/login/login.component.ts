import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { UserdataService } from '../services/userdata.service';

FormControl
@Component({
  selector: 'app-login',
  template: `
  <h1>Login Here</h1>
  <form [formGroup]="Login" (ngSubmit)="OnSubmit()" >
      <div class="form-group" style="margin-bottom:20px">
          <label>Email</label>
          <input type="email" class="form-control" formControlName="email" id="email" name="email">
      </div>
      <div class="form-group">
          <label>Password</label>
          <input type="password" class="form-control" formControlName="password" name="password">
      </div>
      <br>
      <button mat-raised-button color="primary" type="submit" >Login</button>
  </form>
  `,
  styles: [
  ]
})
export class LoginComponent implements OnInit {
  Login:FormGroup=new FormGroup({
    email:new FormControl(""),
    password:new FormControl("")
  })
  public userdata:any

  constructor(public dialog:MatDialog,private user:UserdataService) { }

  ngOnInit(): void {
    this.user.getUsers().subscribe((data)=>this.userdata=data)
  }
  OnSubmit(){
    let guest=this.Login.value
    console.log(guest.email)
   

  }


}
