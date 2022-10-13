import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { DataServiceService } from 'src/app/services/data-service.service';
import { ContactusComponent } from '../contactus/contactus.component';
import { FormsDefinition } from '../Forms';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginVerificationSubscription!: Subscription;
  authenticationSubscription!: Subscription;
  routeSsubscription!: Subscription;
  backendError: string | null | undefined;

  constructor(private route: Router,
    private auth: AuthService,
    private activatedRoute: ActivatedRoute,
    private dataService: DataServiceService
    ) {
      this.authenticationSubscription = this.auth.isAuthenticated.subscribe(res => {
        if(res)
          this.route.navigate(['admin'])
      })
  }

  loginForm: FormGroup = new FormGroup({
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),

  });
  get formObj(){
    return this.loginForm.controls
  }

  ngOnInit(): void {
    // this.routeSsubscription = this.activatedRoute.params.subscribe(data => {
    //   this.authenticationSubscription = this.auth.isAuthenticated.subscribe(res => {
    //     if(res)
    //       this.route.navigate(['admin'])
    //   })
    // })
    // console.log(new ContactusComponent(this.dataService));
  }

  submitLogin() {
    if(this.loginForm.valid){
      this.loginVerificationSubscription = this.auth.loginVerification(this.loginForm.value).subscribe(
        data =>{
          this.backendError = null;
          let returnUrl = this.activatedRoute.snapshot.queryParamMap.get('returnUrl');
          this.route.navigate([ returnUrl || 'user' ])
        },
        err => this.backendError = err.error.detail
      )
    }else{
      console.log("Invalid Login data");
    }
  }


  ngOnDestroy() {
    if(this.loginVerificationSubscription){
      this.loginVerificationSubscription.unsubscribe()
    }
    if(this.authenticationSubscription){
      this.authenticationSubscription.unsubscribe()
    }
    if(this.routeSsubscription){
      this.routeSsubscription.unsubscribe()
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
