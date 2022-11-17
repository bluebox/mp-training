import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  appointments :any;
  confirmappointments : any;
  completeappointments:any;
  rejectappointments : any;
  displayedColumns :string[]=['Appointment_id','Time_of_appointment','appointment_date','Appointment_Status','client_id','emp_id','services','update'];

  constructor(private http : HttpserviceService) { }


  ngOnInit(): void {
    this.subscription =this.http.getappointments().subscribe((data) =>{this.appointments = data ;console.log(data)});
    this.subscription =this.http.getConfirmAppointments().subscribe((data) =>{this.confirmappointments = data ;console.log(data)});
    this.subscription =this.http.getCompleteAppointmentas().subscribe((data) =>{this.completeappointments = data ;console.log(data)});
    this.subscription =this.http.getRejectAppointments().subscribe((data) =>{this.rejectappointments = data ;console.log(data)});
  }

}
