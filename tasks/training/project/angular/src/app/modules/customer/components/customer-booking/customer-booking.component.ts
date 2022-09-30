import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { CustomerServiceService } from '../../customer-service.service';
@Component({
  selector: 'app-customer-booking',
  templateUrl: './customer-booking.component.html',
  styleUrls: ['./customer-booking.component.css']
})
export class CustomerBookingComponent implements OnInit {
  slots: string[] = ['10 AM', "1 PM", '4 PM']
  branches: any

  constructor(private http: CustomerServiceService, private router: Router, private httpUser: HttpServiceService) { }
  bookAppointmentForm: FormGroup = new FormGroup({

    branch: new FormControl("", Validators.required),
    slot: new FormControl(" ", Validators.required),

  })


  ngOnInit(): void {
    this.httpUser.getBranches().subscribe(data => {
      this.branches = data
      console.log(this.branches)
    })
  }
  bookAppointment() {
    console.log(this.bookAppointmentForm.value);
    this.http.bookAppointment({ 'form': this.bookAppointmentForm.value, 'username': this.httpUser.getData('username') }).subscribe(data => {
      console.log(data);

    })
  }

}
