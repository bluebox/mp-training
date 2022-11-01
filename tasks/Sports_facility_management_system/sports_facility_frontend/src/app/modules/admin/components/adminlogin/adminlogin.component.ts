import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FacilityService } from 'src/app/modules/facilities/services/facility.service';
import { AdminService } from '../../services/admin.service';
@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css'],
})
export class AdminloginComponent implements OnInit {
  create_admin_login_form = new FormGroup({
    user_phone: new FormControl('', [Validators.required]),
    user_password: new FormControl('', [Validators.required]),
  });
  formNotValid: boolean = false;
  servererror: any;
  token_status: any;
  constructor(
    private Adminservice: AdminService,
    private router: Router,
    private facilityService: FacilityService
  ) {
    let token = localStorage.getItem('admin_refresh_token');
    console.log(token)
    this.facilityService.CheckRefreshToken(token).subscribe((data) => {
      this.token_status = data;
      console.log(data);
      if (this.token_status != 'access token does not exist') {
        this.router.navigate(['admin/home']);
        console.log("sucess")
      }
    });
  }

  ngOnInit(): void {
    
  }
  submit(): void {
    if (this.create_admin_login_form.valid) {
      this.Adminservice.adminlogin(
        this.create_admin_login_form.value
      ).subscribe(
        (data) => {
          console.log(data);
          let tokens = JSON.stringify(data);
          let Parsed = JSON.parse(tokens);
          localStorage.setItem('admin_refresh_token', Parsed.refresh_token);
          this.router.navigate(['admin/home']);
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
