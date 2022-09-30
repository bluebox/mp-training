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
    facility_phone: new FormControl('', [Validators.required]),
  });
  facility_details: any;
  msg_details: any;
  errormsg: any;

  constructor(private service: AdminService, private router: Router) {}

  ngOnInit(): void {}
  submit(): void {
    this.service
      .postFacility(this.create_facility_form.value)
      .subscribe((data: any) => {
        (this.facility_details = data.facility_details),
          (this.msg_details = data.msg),(this.errormsg = JSON.stringify(data));
      })
      if (this.facility_details) {
        console.log(this.facility_details.facility_id);
        console.log(this.msg_details)
        window.sessionStorage.setItem(
          'facility_id',
          this.facility_details.facility_id
        )
        this.router.navigate(['addsports'])
      } else if (this.errormsg)  {
        console.log(this.errormsg);
      }
      ;
  }
  ngDoCheck(): void {
    if (this.facility_details) {
      console.log(this.facility_details.facility_id);
      console.log(this.msg_details)
      window.sessionStorage.setItem(
        'facility_id',
        this.facility_details.facility_id
      );
    } else if (this.errormsg)  {
      console.log(this.errormsg);
    }
  }
}
