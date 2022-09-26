import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  receivedData : string = ''
  login : any;
  constructor(private rroutee : ActivatedRoute) { }

  ngOnInit(): void {
    // this.userdata = new FormGroup({
    //   'firstname' : new FormControl ('',[Validators.required,Validators.maxLength(25)]),
    //   'lastname' : new FormControl('', [Validators.required,Validators.maxLength(25)])
    // }),
    this.login = new FormGroup({
      'emailid' : new FormControl ('',[Validators.required,Validators.email]),
      'password' : new FormControl ('',[Validators.required,Validators.maxLength(25)])
    })
  }
  loginSubmit(){
    console.log(this.login.value)
   }
  
}
