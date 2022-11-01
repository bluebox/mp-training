import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  constructor(private userService: UserService, private router: Router) {}
  formNotValid: boolean = false;
  servererror: string ="";
  ngOnInit(): void {}

  SignUpForm: FormGroup = new FormGroup({
    user_name: new FormControl('', [Validators.required]),
    user_email: new FormControl('', [Validators.required, Validators.email]),
    user_phone: new FormControl('', [Validators.required]),
    user_password: new FormControl('', [Validators.required,Validators.minLength(8)]),
  });

  onSignUp() {
    if (this.SignUpForm.valid) {
      console.log(this.SignUpForm.value);
      this.userService.createUser(this.SignUpForm.value).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(['facilities/hyderabad']);
      },
      (err) => {
        // console.log(err)
        this.servererror=err.error['user_phone'][0];
        
      });
    } else {
      this.formNotValid = true;
    }
  }
}
