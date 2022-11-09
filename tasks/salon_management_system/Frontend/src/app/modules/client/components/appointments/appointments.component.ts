import { Component, OnInit } from '@angular/core';
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
  displayedColumns :string[]=['Appointment_id','Time_of_appointment','appointment_date','Appointment_Status','client_id','emp_id','services','button'];
  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getCompleteAppointmentas().subscribe((data) =>{this.completeappointments = data ;console.log(data)});
  }

}
