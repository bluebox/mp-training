import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
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
    User_name: new FormControl("",Validators.required),
    User_email: new FormControl(""),
    password: new FormControl(""),
    User_phone: new FormControl(""),
    User_DOB:new FormControl(""),
    User_Address:new FormControl("")
  
  })
  gender=["male","female","other"]
  UserModel=new User("Nikita","nikita.singh1713@gmail.com",123456789,"female","16/01/2001","Attapur,Hyderabad")
  longText = `The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog
  from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was
  originally bred for hunting.`;
  
  constructor(private router:Router,private route:ActivatedRoute,public dialog:MatDialog,public rdialog:MatDialogRef<SignupComponent>,private user:UserdataService) { }
  
  ngOnInit(): void {

  }
  moviepage(){
    
    this.router.navigate(['signup'],{relativeTo:this.route})
  }
  OnSubmit(data:any){
    if(this.Signup.value.password==document.getElementById("confirm")){
    this.user.saveUser(this.Signup.value).subscribe(data=>console.log(data))
    this.dialog.open(LoginComponent);
    console.log(this.Signup.value)}
    else{
      alert("Fill the form correctly!!")
    }
  }
  openLogin() {
   
    this.rdialog.close();
  }

}
