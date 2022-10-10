import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title="Login";
  log_email!: string;
  log_password!: string;
  sign_email!: string;
  sign_password!: string;
  sign_con_password!: string;

  constructor() { }

  ngOnInit(): void {
  }

}
