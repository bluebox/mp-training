import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {  FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


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
    let postData = {
      username: "sairam",
      email: "sairamyadhav4@gmail.com",
      password: "sairam"
    }

    // Access-Control-Allow-Headers: Content-Type
    // Access-Control-Allow-Methods: GET, POST, OPTIONS
    // Access-Control-Allow-Origin: *

    const headers = new HttpHeaders().set("Access-Control-Allow-Origin", '*')

    this.http.post("http://127.0.0.1:8000/api/register/", postData, {headers:headers}).subscribe((data) => {
      this.res = data;
      // localStorage.setItem('currentUser', JSON.stringify({ token: this.res.token, name: postData.username }));
    })
  }

  

  onSubmit() {
      console.warn(this.registrationForm.valid)
      let data = {
        username: this.registrationForm.value.username,
        email: this.registrationForm.value.email,
        password: this.registrationForm.value.password1
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
      console.log(this.registrationForm.value)
      this.http.post("http://127.0.0.1:8000/api/register/", data, {headers:headers}).subscribe((data) => {
        this.res = data;
        localStorage.setItem('currentUser', JSON.stringify({ token: this.res.token, name: this.registrationForm.value.username }));
        this.router.navigate(['login'])
      })
  }

  ngOnInit(): void {
  }

}
