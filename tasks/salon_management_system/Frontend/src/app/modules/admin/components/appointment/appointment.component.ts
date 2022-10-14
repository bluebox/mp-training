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
  displayedColumns :string[]=['Appointment_id','Time_of_appointment','appointment_date','Appointment_Status','client_id','emp_id','services'];

  constructor(private http : HttpserviceService) { }


  ngOnInit(): void {
    this.subscription =this.http.getappointments().subscribe((data) =>{this.appointments = data ;console.log(data)});
  }

}
