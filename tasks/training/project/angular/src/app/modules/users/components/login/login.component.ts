import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpServiceService } from '../../http-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {
  formNotValid= false
  errorMessage = ''
  constructor(private http: HttpServiceService,
    private router : Router) { }

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl("", Validators.required)
  })
  handleError(err:string) {
    alert(err)

  }
  submitLogin() {
    if (this.loginForm.valid) {
        this.http.loginUser(this.loginForm.value).subscribe({
          next: (resp) => {
            console.log(resp)
            this.errorMessage = resp.msg
            if (this.errorMessage == "logged in") {
              this.http.saveData("username", resp.user)
              // console.log(this.http.getData('username'));
              localStorage.setItem('user', JSON.stringify(resp.user))
              this.router.navigate([''])
            }
          },
          error: (err) => {
            console.log(err.data);
            this.handleError(err.message)
          }
        })
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      console.log(this.loginForm.valid);

    }
  }


}
