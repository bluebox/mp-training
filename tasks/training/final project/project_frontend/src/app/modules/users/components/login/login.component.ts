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

          // this.http.saveData("username",resp.user)


          localStorage.setItem('user', JSON.stringify(resp.user))
          localStorage.setItem('username', (resp.user))
          console.log(localStorage.getItem("username"))

          localStorage.setItem('user_type', (resp.user_type))
          console.log(localStorage.getItem("user_type"))
          this.k = localStorage.getItem("user_type");

          console.log(typeof(localStorage.getItem("user_type")));
          console.log(this.k)
          

          // if (localStorage.getItem("user_type") === "Student") {
          if(this.k == "Student"){

            console.log("te11");

            this.router.navigate(['student']);
          }

          else if(localStorage.getItem("user_type") == "Teacher") {
       

            console.log("te");

            this.router.navigate(['teacher']);
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
