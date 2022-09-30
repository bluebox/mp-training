import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;

  loginForm!: FormGroup;
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]);

  constructor(private formBuilder: FormBuilder, private router: Router, private auth: AuthService, private user: UserService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: this.email,
      password: this.password
    });
  }

  onSubmit(): void {
    console.log(this.loginForm.value);


    try {
      this.auth.login(this.loginForm.value).subscribe(res => {

        let data = JSON.stringify(res);
        let obj = JSON.parse(data);
        this.user.loginUser = obj
        this.user.isAuthenticated = true;

        this.router.navigate(['/admin/myProfile']);

      }
      );
    }
    catch (error) {
      console.log(error);

    }
  }

}


