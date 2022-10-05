import { JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private route: Router,
    private http: HttpClient,
    private auth: AuthService
    ) { }

  shortLink: string = "";
  loading: boolean = false; // Flag variable
  subscription!: Subscription
  authToken!: string


  SignUpForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    email : new FormControl('', [Validators.required, Validators.email]),
    password : new FormControl('', [Validators.required]),
    confirm_password : new FormControl('', [Validators.required]),
    mobile : new FormControl('', [Validators.required]),
  })

  // uploadPic: FormGroup = new FormGroup({
  //   profile: new FormControl()
  // })

  submitSignUp() {
    let userObj = this.SignUpForm.value
    if(userObj.password == userObj.confirm_password){
      delete userObj.confirm_password
      this.subscription = this.auth.registerAndGetToken(this.SignUpForm.value).subscribe(data => {
        let res = JSON.stringify(data)
        let token = JSON.parse(res)
        if(token){
          this.auth.changeAuthentication()
          this.route.navigate([''])
        }
      })
    }else{
      console.log('Passwords not matched');
    }
  }


  NavigateToLoginUp() {
    this.route.navigate(['login'])

  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    if(this.subscription){
      this.subscription.unsubscribe();
    }
  }

}
