import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group (
    {
      username : ['', [Validators.required, Validators.minLength(6)]],
      password : ['', [Validators.required, Validators.minLength(7)]],
      type : ['', [Validators.required, Validators.minLength(7)]]
    }
  )

  constructor(private http:HttpClient, private fb: FormBuilder, private router: Router) { }

  loginSubmit() {
    let data = {
      username : this.loginForm.value.username,
      password: this.loginForm.value.password,
      // type: this.loginForm.value.type
    }

    console.log(data);
    

    let headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
      }
    )

    this.http.post("http://127.0.0.1:8000/api/login", data, {headers:headers}).subscribe((data:any) => {
      console.log(data.token)
      if (data.status == 200) {
        localStorage.setItem('token', data.token);
        localStorage.setItem('username', data.username);
        // localStorage.setItem('admin', data.admin);
        this.router.navigate([''])
      }
      else {
        alert("wrong credentials")
      }
    })

    const token = localStorage.getItem('currentUser')

  }

  ngOnInit(): void {
  }

}
