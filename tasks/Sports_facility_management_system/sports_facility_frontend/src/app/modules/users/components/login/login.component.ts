import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Location } from '@angular/common'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  create_user_login_form = new FormGroup({
    user_phone: new FormControl('', [Validators.required]),
    user_password: new FormControl('', [Validators.required]),
  });
  access_token: any;
  errormsg: any;
  constructor(private services: UserService,private location:Location) {}

  ngOnInit(): void {}
  submit(): void {
    this.services.UserLogin(this.create_user_login_form.value).subscribe(
      (data) => {
        console.log(data);
        let tokens = JSON.stringify(data);
        let Parsed = JSON.parse(tokens);
        localStorage.setItem('refresh_token', Parsed.refresh_token);
        this.location.back()

      },
      (err) => {
        this.errormsg = err.error.detail;
      }
    );
  }
}
