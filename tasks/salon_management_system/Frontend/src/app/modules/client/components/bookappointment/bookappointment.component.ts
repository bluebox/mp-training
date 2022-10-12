import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

interface Time {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-bookappointment',
  templateUrl: './bookappointment.component.html',
  styleUrls: ['./bookappointment.component.css']
})
export class BookappointmentComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  allservices : any;
  employees :any;
  constructor(private http:HttpserviceService) { }
  times: Time[] = [
    {value: '9.00pm-10.00pm', viewValue: '9.00pm-10.00pm'},
    {value: '10.00pm-11.00pm', viewValue: '10.00pm-11.00pm'},
    {value: '11.00pm-12.00pm', viewValue: '11.00pm-12.00pm'},
    {value: '12.00pm-1.0pam', viewValue: '12.00pm-1.0pam'},
    {value: '1.00pm-2.00pm', viewValue: '1.00pm-2.00pm'},
    {value: '2.00pm-3.00pm', viewValue: '2.00pm-3.00pm'},
    {value: '3.00pm-4.00pm', viewValue: '3.00pm-4.00pm'},
    {value:'4.00pm-5.00pm', viewValue: '4.00pm-5.00pm'},
  ];
  timeControl = new FormControl(this.times[1].value);
  bookAppointmentForm : FormGroup = new FormGroup({
    client_id : new FormControl("",Validators.required),
    Time_of_appointment :this.timeControl,
    appointment_date :new FormControl("",Validators.required),
    emp_id : new FormControl("",Validators.required),
    services :new FormControl("",Validators.required)

  })
  ngOnInit(): void {
    this.subscription =this.http.getServices().subscribe((data) =>{this.allservices = data ;console.log(data)});
    this.subscription =this.http.getEmployee().subscribe((data) =>{this.employees = data ;console.log(data)});
  }
  onSubmit(){
    console.log(this.bookAppointmentForm.value)
    this.http.newAppointment(this.bookAppointmentForm.value).subscribe(data => {console.log(data);alert("branch added successfully")})

  }

}
