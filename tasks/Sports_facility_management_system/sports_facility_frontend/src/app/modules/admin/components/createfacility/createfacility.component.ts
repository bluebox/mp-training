import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
@Component({
  selector: 'app-createfacility',
  templateUrl: './createfacility.component.html',
  styleUrls: ['./createfacility.component.css'],
})
export class CreatefacilityComponent implements OnInit {
  create_facility_form = new FormGroup({
    facility_name: new FormControl('', [Validators.required]),
    facility_email: new FormControl('', [Validators.required]),
    facility_password: new FormControl('', [Validators.required]),
    facility_location: new FormControl('', [Validators.required]),
    facility_phone: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}')]),
  });
  facility_details: any;
  msg_details: any;
  errormsg: any;
  servererror: any;
  formNotValid: boolean = false;
  constructor(private service: AdminService, private router: Router) {}

  ngOnInit(): void {}
  submit(): void {
    if (this.create_facility_form.valid) {
      this.service.postFacility(this.create_facility_form.value).subscribe(
        (data: any) => {
          console.log(data);
        },
        (err) => {
          this.servererror = err;
        }
      );
    }
    else{
      this.formNotValid=true;
    }
  }
}
