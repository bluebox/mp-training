import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-emp-login',
  templateUrl: './emp-login.component.html',
  styleUrls: ['./emp-login.component.css']
})
export class EmpLoginComponent implements OnInit {
  hide = true;

  loginForm!: FormGroup;
  email = new FormControl('', [Validators.email, Validators.required]);
  password = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]);

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: this.email,
      password: this.password
    });
  }

  onSubmit(): void {
    console.log(this.loginForm.value);

  }
}
