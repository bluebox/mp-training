import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

interface Status {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-updateappointment',
  templateUrl: './updateappointment.component.html',
  styleUrls: ['./updateappointment.component.css']
})
export class UpdateappointmentComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  id:any;

  Statuses: Status[] = [
    {value:'Booked', viewValue:'Booked'},
    {value:'confirm' , viewValue:'confirm' },
    {value: 'complete', viewValue: 'complete'},
    {value:'reject', viewValue:'reject'},
  ];

  constructor(private http:HttpserviceService,private router:ActivatedRoute,private route:Router) { }

  updateAppointmentForm : FormGroup = new FormGroup({
    client_id : new FormControl("",Validators.required),
    Time_of_appointment : new FormControl("",Validators.required),
    appointment_date : new FormControl("",Validators.required),
    Appointment_Status : new FormControl(this.Statuses[1].value),
    emp_id : new FormControl("",Validators.required),
    services : new FormControl("",Validators.required)
  })

  ngOnInit(): void {
    this.http.getCurrentAppointment(this.router.snapshot.params.id).subscribe((result : any)=>{
    this.updateAppointmentForm = new FormGroup({
      client_id : new FormControl(result['client_id'],Validators.required),
      Time_of_appointment : new FormControl(result['Time_of_appointment'],Validators.required),
      appointment_date : new FormControl(result['appointment_date'],Validators.required),
      Appointment_Status : new FormControl(result["Appointment_Status"],Validators.required),
      emp_id : new FormControl(result['emp_id'],Validators.required),
      services : new FormControl(result['services'],Validators.required)
    })
    })
  }


  onSubmit(){
    this.http.updateAppointment(this.router.snapshot.params.id,this.updateAppointmentForm.value).subscribe((result)=>{
      console.log(result);alert("updated successfully");this.route.navigate(['admin/appointment'])
    })
  }
}
