import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  constructor(private formBuilder: FormBuilder, private router: Router, private auth: AuthService, private user: UserService) {

  }

  updateForm!: FormGroup;
  email = new FormControl(this.user.loginUser.email, [Validators.email, Validators.required]);
  firstName = new FormControl(this.user.loginUser.firstName);
  lastName = new FormControl(this.user.loginUser.lastName);
  mobileNo = new FormControl(this.user.loginUser.mobileNo, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]);
  occupation = new FormControl(this.user.loginUser.occupation, [Validators.required, Validators.maxLength(30)]);
  address = new FormControl(this.user.loginUser.address, [Validators.minLength(5), Validators.maxLength(200)]);



  ngOnInit(): void {
    this.updateForm = this.formBuilder.group({
      email: this.email,
      firstName: this.firstName,
      lastName: this.lastName,
      mobileNo: this.mobileNo,
      occupation: this.occupation,
      address: this.address,
    })
  }

  onSubmit() {

    try {
      this.auth.updateProfile(this.updateForm.value).subscribe(
        data => {
          window.alert("Profile updated successfully!");
          this.user.loginUser.email = this.updateForm.value.email;
          this.user.loginUser.firstName = this.updateForm.value.firstName;
          this.user.loginUser.lastName = this.updateForm.value.lastName;
          this.user.loginUser.mobileNo = this.updateForm.value.mobileNo;
          this.user.loginUser.occupation = this.updateForm.value.occupation;
          this.user.loginUser.address = this.updateForm.value.address;
          this.router.navigate(['admin/myProfile']);

        }
      )
    }
    catch (error) {
      console.log(error);
    }
  }
}


