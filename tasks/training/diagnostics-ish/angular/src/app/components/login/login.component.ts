import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthInterceptor } from 'src/app/interceptors/auth.interceptor';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { SubjectServiceService } from 'src/app/services/subject-service/subject-service.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  hide: boolean = true;
  formNotValid: boolean = false;
  responseMessage: string = '';
  loginSubscription!: Subscription;
  constructor(private http: HttpServiceService, private router: Router, private subjectService: SubjectServiceService,private activatedRoute : ActivatedRoute) { }

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  handleError(err: any) {
    // alert(err)
  }
  submitLogin() {
    this.loginForm.get('username')?.setValue(this.loginForm.get('username')?.value.trim())
    this.loginForm.get('password')?.setValue(this.loginForm.get('password')?.value.trim())


    if(this.loginForm.valid){
      this.loginSubscription = this.http.loginUser(this.loginForm.value).subscribe({
        next: (resp: any) => {
          this.responseMessage = resp.message
          if (resp.message == "success") {
            AuthInterceptor.accessToken = resp.token;
            window.localStorage.setItem("user_type_id",resp.user_type_id)
            window.localStorage.setItem("username",resp.username)
            window.localStorage.setItem("user_type",resp.user_type)
            this.subjectService.sendLoginDetails({
              'user_type': resp.user_type,
              'username': resp.username,
              'user_type_id': resp.user_type_id
            })
            if(resp.user_type == "admin"){
              let returnUrl = this.activatedRoute.snapshot.queryParamMap.get('returnUrl');
              this.router.navigate([returnUrl || 'admin'])
            }
            if(resp.user_type == "customer"){
              this.router.navigate([""])
          

            }
            if(resp.user_type == "admin"){

              this.router.navigate(["admin"])
            }
          }
        },
        error: (err: any) => {
          console.log(err);
        }
      })
    }
    else{
      this.formNotValid = true
    }

  }

  ngOnInit(): void { }

  ngOnDestroy(){
    if(this.loginSubscription){
      this.loginSubscription.unsubscribe()
    }
  }

  
}
