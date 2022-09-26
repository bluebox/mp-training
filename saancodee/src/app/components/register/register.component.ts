import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {  FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from 'express';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  authToken!:any;
  registrationForm = this.fb.group(
    {
      username : ['', [Validators.required, Validators.minLength(6)]],
      email : ['', [Validators.required, Validators.email]],
      password1 : ['', [Validators.required, Validators.minLength(7)]],
      password2 : ['', [Validators.required, Validators.minLength(7)]],
    }
    )
    res:any;
    
    constructor(private http: HttpClient, private fb: FormBuilder, private router: Router) { }

  register() {
    let data = {
      username: "sairam",
      email: "sairamyadhav4@gmail.com",
      password1: "sairam",
      password2: "sairam"
    }

    // Access-Control-Allow-Headers: Content-Type
    // Access-Control-Allow-Methods: GET, POST, OPTIONS
    // Access-Control-Allow-Origin: *

    const headers = new HttpHeaders().set("Access-Control-Allow-Origin", '*')

    this.http.post("http://127.0.0.1:8000/api/register/", data, {headers:headers}).subscribe((data) => {
      this.res = data;
      console.log(data);
    })
  }

  

  onSubmit() {
      console.warn(this.registrationForm.valid)
      let data = {
        username: this.registrationForm.get('username'),
        email: this.registrationForm.get('email'),
        password: this.registrationForm.get('password')
      }
  
      // Access-Control-Allow-Headers: Content-Type
      // Access-Control-Allow-Methods: GET, POST, OPTIONS
      // Access-Control-Allow-Origin: *

      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
      })
      //   'Access-Control-Allow-Credentials':"true"});
    // let options = { headers: headers };
  
      // const headers = new HttpHeaders().set("Access-Control-Allow-Origin", '*')
      // headers.set('Access-Control-Allow-Credentials', true)
  
      this.http.post("http://127.0.0.1:8000/api/register/", data, {headers:headers}).subscribe((data) => {
        this.res = data;
        console.log(data);
      })
  }

  ngOnInit(): void {
  }

}
