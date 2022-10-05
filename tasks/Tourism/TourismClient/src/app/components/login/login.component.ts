import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { FormsDefinition } from '../Forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private route: Router,
    private auth: AuthService
    ) {
      if(this.auth.isAuthenticated){
        this.route.navigate(['user'])
      }
  }

  email!: FormControl
  password!: FormControl

  loginForm: FormGroup = new FormGroup({
    email : new FormControl(this.email, [Validators.required, Validators.email]),
    password : new FormControl('', [Validators.required])
  })

  ngOnInit(): void {

  }

  submitLogin() {
    this.subscription = this.auth.loginVerification(this.loginForm.value).subscribe(data=>{
      let res = JSON.stringify(data)
      let token = JSON.parse(res)
      console.log(token['access_token']);
      console.log(this.auth.getDecodedAccessToken(token['access_token']));
      // console.log((this.auth.getCookie('refresh_token')));
      this.auth.changeAuthentication();
      this.route.navigate(['user'])
    })
    // if(this.auth.getCookie('refresh_token')){
    //   console.log(this.auth.getCookie('refresh_token'));
    //   console.log(this.auth.isAuthenticated);
    //   if(this.auth.isAuthenticated){
    //     this.route.navigate(['user'])
    //   }
    // }

  }

  subscription!: Subscription;

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe()
    }
  }

  navigate() {
    this.route.navigate(['user'])
  }

  // submitLogin() {
  //   console.log(this.loginForm.value);
  //   this.auth.loginVerification(this.loginForm.value)
  // }

  NavigateToSignUp() {
    this.route.navigate(['signup'])
  }

}
