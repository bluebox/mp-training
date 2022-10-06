import { Component} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpserviceService } from 'src/app/httpservice.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  hide = true;
  formNotValid : boolean = false;
  formError ?: string =""
  errorMessage : string = ""
  
  constructor(private http: HttpserviceService,
    private router : Router) { }

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl("", Validators.required)
  })

  onLoginSubmit(){

  }
  get passwordInput() { return this.loginForm.get('password');} 

}
