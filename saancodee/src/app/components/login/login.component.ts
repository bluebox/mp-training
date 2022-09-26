import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

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

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  }

}
