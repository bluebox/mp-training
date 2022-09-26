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
      password : ['', [Validators.required, Validators.minLength(7)]]
    }
  )
  res: Object | undefined;

  constructor(private http:HttpClient, private fb: FormBuilder, private router: Router) { }

  loginSubmit() {
    let data = {
      username : this.loginForm.value.username,
      password: this.loginForm.value.password
    }

    let headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
      }
    )

    this.http.post("http://127.0.0.1:8000/api/login", data, {headers:headers}).subscribe((data) => {
      this.res = data
      console.log(this.res)
      this.router.navigate(['profile/:username'])
    })

    const token = localStorage.getItem('currentUser')

  }

  ngOnInit(): void {
  }

}
