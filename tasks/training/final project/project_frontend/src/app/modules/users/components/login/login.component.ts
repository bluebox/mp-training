import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../http-service.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formNotValid: boolean = false
  errorMessage: string = ''
  k: any
  hide:boolean=true
  user_type:any



  constructor(private http: HttpServiceService,
    private router: Router) { }


  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl("", Validators.required)
  })

  handleError(err: any) {

  }

  submitLogin() {
    if (this.loginForm.valid) {
      this.http.loginUser(this.loginForm.value).subscribe(resp => {
        console.log(resp)
        this.errorMessage = resp.msg
        this.user_type=resp.user_type
        
        if (this.errorMessage == "logged in") {

          localStorage.setItem('username', (resp.user))
          
          if(this.user_type == "Student"){
            this.router.navigate(['student']);
          }

          else if(this.user_type == "Teacher") {
            this.router.navigate(['teacher']);
          }
          else if(this.user_type == "Admin") {
            this.router.navigate(['admin']);
          }

        }
      },
        err => {
          console.log(err.data);
          this.handleError(err.message)
        }
      )
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      console.log(this.loginForm.valid);
    }

  }

  ngOnInit(): void {

  }

}
