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
  k: string = ''



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
        if (this.errorMessage == "logged in") {


          localStorage.setItem('user', JSON.stringify(resp.user))
          localStorage.setItem('user_type', JSON.stringify(resp.user_type))
          console.log(localStorage.getItem("user_type"))

          localStorage.getItem("user_type")

          if(localStorage.getItem("user_type") === "Student") {

            console.log("te");

            this.router.navigate(['registerstudent']);
          }

          else if(localStorage.getItem("user_type") == "Teacher") {
            // else{

            console.log("te");

            this.router.navigate(['registerteacher']);
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
