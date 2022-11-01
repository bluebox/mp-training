import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Location } from '@angular/common';
import { FacilityService } from 'src/app/modules/facilities/services/facility.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  create_user_login_form = new FormGroup({
    user_phone: new FormControl('', [Validators.required]),
    user_password: new FormControl('', [Validators.required]),
  });
  access_token: any;
  servererror: any;
  formNotValid: boolean = false;
  constructor(
    private services: UserService,
    private location: Location,
    private facilityService: FacilityService,
    private router: Router
  ) {
    let token = localStorage.getItem('refresh_token');
    this.facilityService.CheckRefreshToken(token).subscribe((data) => {
      let res = JSON.stringify(data);
      let Parsed = JSON.parse(res);
      if (Parsed.is_admin) {
        this.router.navigate(['admin/home']);
      } else {
        this.router.navigate(['facilities/hyderabad']);
      }
    });
  }

  ngOnInit(): void {}
  submit(): void {
    if (this.create_user_login_form.valid) {
      this.services.UserLogin(this.create_user_login_form.value).subscribe(
        (data) => {
          console.log(data);
          let tokens = JSON.stringify(data);
          let Parsed = JSON.parse(tokens);
          localStorage.setItem('refresh_token', Parsed.refresh_token);
          if (Parsed.is_admin) {
            // this.facilityService.is_admin=true;
            this.router.navigate(['admin/home']);
          } else {
            // this.facilityService.is_user=true;
            this.location.back();
          }
        },
        (err) => {
          this.servererror = err.error.detail;
        }
      );
    } else {
      this.formNotValid = true;
    }
  }
}
