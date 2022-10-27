import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import { LoginComponent } from '../login/login.component';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import { UserdataService } from '../services/userdata.service';

LoginComponent



@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  Signup:FormGroup =new FormGroup({
    User_name: new FormControl("",[Validators.required,Validators.pattern('^[A-Za-z]{4}')]),
    User_email: new FormControl("",[Validators.required, Validators.email]),
    password: new FormControl("",[Validators.required,Validators.minLength(4)]),
    User_phone: new FormControl("",[Validators.required,Validators.pattern('^[0-9]{10}')]),
    User_DOB:new FormControl("",Validators.required),
    User_Address:new FormControl("",Validators.required)
  
  })
  
  constructor(private router:Router,private route:ActivatedRoute,public dialog:MatDialog,public rdialog:MatDialogRef<SignupComponent>,private user:UserdataService) { }
  
  ngOnInit(): void {

  }


  moviepage(){
    this.router.navigate(['signup'],{relativeTo:this.route})
  }


  OnSubmit(data:any){
    if(this.Signup.valid){
    if(this.Signup.value.password==document.getElementById("confirm")){
    this.user.saveUser(this.Signup.value).subscribe(data=>console.log(data))
    this.dialog.open(LoginComponent);}
    else{
      console.log(this.Signup.value,document.getElementById("confirm")?.innerHTML)
      alert("Fill the form correctly!!")
    }
  }
  else{
    alert("invalid data, fill the form correctly")
  }
  }


  openLogin() {
    this.dialog.closeAll()
    this.dialog.open(LoginComponent)
    this.rdialog.close();
  }

}
