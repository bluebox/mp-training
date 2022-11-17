import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  subscription : Subscription = Subscription.EMPTY
  completeappointments:any;
  confirmappointments:any;
  id:any;
  displayedColumns :string[]=['Appointment_id','Time_of_appointment','appointment_date','Appointment_Status','client_id','emp_id','services','button'];
  desiredColumns :string[]=['Appointment_id','Time_of_appointment','appointment_date','Appointment_Status','client_id','emp_id','services','button'];
  constructor(private http : HttpserviceService,private router:ActivatedRoute,private route:Router) { }
  updateAppointmentForm : FormGroup = new FormGroup({
    client_id : new FormControl("",Validators.required),
    Time_of_appointment : new FormControl("",Validators.required),
    appointment_date : new FormControl("",Validators.required),
    Appointment_Status : new FormControl(""),
    emp_id : new FormControl("",Validators.required),
    services : new FormControl("",Validators.required)
  })

  ngOnInit(): void {
    this.subscription =this.http.getCompleteAppointmentas().subscribe((data) =>{this.completeappointments = data ;console.log(data)});
    this.subscription =this.http.getConfirmAppointments().subscribe((data) =>{this.confirmappointments = data ;console.log(data)});
  }

}
