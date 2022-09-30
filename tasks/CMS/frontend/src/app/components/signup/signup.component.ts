import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordChecker } from 'src/app/CustomValidators/password-checker';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private auth: AuthService) { }

  hide = true;
  userForm!: FormGroup;
  email = new FormControl('', [Validators.email, Validators.required]);
  firstName = new FormControl('');
  lastName = new FormControl('');
  // username = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]);
  password = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]);
  confirm_password = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]);
  mobileNo = new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]);
  occupation = new FormControl('', [Validators.required, Validators.maxLength(30)]);
  roleId = "User";
  address = new FormControl('', [Validators.minLength(5), Validators.maxLength(200)]);
  user_type = "owner";
  image = new FormControl('', [Validators.required]);


  get f() {
    return this.userForm.controls;
  }

  // onFileChange(event) {

  //   if (event.target.files.length > 0) {
  //     const file = event.target.files[0];
  //     this.userForm.patchValue({
  //       fileSource: file
  //     });
  //   }
  // }
  ngOnInit(): void {

    this.userForm = this.formBuilder.group({
      email: this.email,
      firstName: this.firstName,
      lastName: this.lastName,
      password: this.password,
      confirm_password: this.confirm_password,
      mobileNo: this.mobileNo,
      occupation: this.occupation,
      address: this.address,
      roleId: this.roleId,
      user_type: this.user_type,
      // add image
      image: this.image



    }, {
      validator: PasswordChecker('password', 'confirm_password')
    })
  }

  onReset(): void { }

  onSubmit(): void {
    console.log(this.userForm.value);

    try {
      this.auth.registerUser(this.userForm.value).subscribe(res => {
        console.log(res);
        this.router.navigate(['/login']);
      }
      );
    }
    catch (error) {
      console.log(error);

    }
  }


}
