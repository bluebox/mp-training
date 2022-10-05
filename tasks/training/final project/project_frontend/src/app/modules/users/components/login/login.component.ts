import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../http-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formNotValid: boolean =false
  errorMessage: string =''



  constructor(private http: HttpServiceService,
    private router : Router) { }


  loginForm: FormGroup = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl("", Validators.required)
    })
  
  handleError(err: any) {
      // alert(err)
  
    }

  submitLogin() {
    if (this.loginForm.valid) {
      this.http.loginUser(this.loginForm.value).subscribe(resp => {
        console.log(resp)
        this.errorMessage = resp.msg
        if(this.errorMessage == "logged in"){
          this.router.navigate(['registerstudent'])
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
