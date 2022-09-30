import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [ServicesService]
})
export class SignupComponent implements OnInit {
  loginformcontrols: any;

  constructor(private services:ServicesService) { }
  gender: Gender[] = [
    {value: 'male', viewValue: 'Male'},
    {value: 'female', viewValue: 'Female'},
  ];

  classnames: ClassName[] = [
    {classname : 'B. Tech'},
    {classname : 'M. Tech'},
    {classname : 'MCA'},
  ];

  users: Users[] = [
    {users : 'Faculty'},
    {users : 'Student'},
  ];
  ngOnInit(): void {
    this.loginformcontrols = {
      firstname:'',
      lastname:'',
      username:'',
      password:'',
      gender:'',
      roll:0,
      fathername:'',
      class:'',
      usertype:''

    }
  }


  // loginform = new FormGroup({
  //   firstname : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]+$')]),
  //   lastname : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]+$')]),
  //   username : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]+$')]),
  //   password : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z0-9]+$'), Validators.minLength(6)]),
  //   gender : new FormControl('', [Validators.required, Validators.email]),
  //   fathername : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]+$')]),
  //   rollno : new FormControl('', [Validators.required, Validators.pattern('[0-9]')]),
  //   class : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z0-9]')]),
  //   usertype : new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z]')]),

  // }
  // )
  // get firstname(){
  //   return this.loginform.get("firstname");
  // }
  // get lastname(){
  //   return this.loginform.get("lastname");
  // }
  // get username(){
  //   return this.loginform.get("username");
  // }
  // get password(){
  //   return this.loginform.get("password");
  // }
  // proceed(){
  //   console.warn(this.loginform.value)
  // }
      // if(this.loginform.invalid)
    //   alert("Registeration NOT Successfull, ");
    // else
    //   alert("Registeration Successfull")
    
  registerNewUser(){
    this.services.registerNewStudent(this.loginformcontrols).subscribe(
      response => {
        alert("User created Successfully");
      },
      error => console.log("Error ",error)
    );

  }
}

interface Gender {
  value: string;
  viewValue: string;
}

interface ClassName {
  classname:string;
}

interface Users {
  users:string;
}