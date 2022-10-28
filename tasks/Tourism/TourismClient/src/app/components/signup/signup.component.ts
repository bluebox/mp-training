import { JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private route: Router,
    private http: HttpClient,
    private auth: AuthService,
    private dataService: DataServiceService
    ) { }

  imageUrl: string | undefined;
  loading: boolean = false; // Flag variable
  subscription!: Subscription
  imagesSubscription!: Subscription
  backendError: string | null | undefined;

  SignUpForm: FormGroup = new FormGroup({
    name : new FormControl('', [Validators.required]),
    email : new FormControl('', [Validators.required, Validators.email]),
    image : new FormControl(''),
    password : new FormControl('', [Validators.required]),
    confirm_password : new FormControl('', [Validators.required]),
    mobile : new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}')]),
  })

  get formObj() {
    return this.SignUpForm.controls
  }

  PasswordMatchCheck(){
    if(this.formObj['password'] != this.formObj['confirm_password']){
      return 'Passwords not matched'
    }else{
      return null;
    }
  }

  onchange(e:any){
    console.log(e.target.files[0]);
    this.imagesSubscription = this.dataService.uploadImage(e.target.files[0]).subscribe(
      (data:any) => {
        // let dataString = JSON.stringify(data)
        // this.imageUrl = JSON.parse(dataString)
        this.imageUrl = data
        console.log(this.imageUrl);
      },
      err => {
        if(err.status == 404 || 500){
          alert(err.message)
        }
        else{
          alert(err.error.detail)
        }
      }
    )
  }



  submitSignUp() {
    if(this.SignUpForm.valid && this.PasswordMatchCheck() != null){
      let userObj = this.SignUpForm.value
      if(userObj.password == userObj.confirm_password){
        delete userObj.confirm_password
        this.SignUpForm.get('image')?.setValue(this.imageUrl)
        this.subscription = this.auth.registerAndGetToken(this.SignUpForm.value).subscribe(
          data => {
            let res = JSON.stringify(data)
            let token = JSON.parse(res)
            this.backendError = null
            if(token){
              this.route.navigate(['user'])
            }
          },
          err => {
            if(err.status == 404 || 500){
              alert(err.message)
            }
            else{
              this.backendError = err.error.detail
            }
          }
        )
      }else{
        this.backendError = 'Passwords not matched'
      }
    }else{
      console.log("invalid data");
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
    if(this.imagesSubscription){
      this.imagesSubscription.unsubscribe();
    }
  }

}
