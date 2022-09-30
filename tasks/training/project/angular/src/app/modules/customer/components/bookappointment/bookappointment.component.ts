import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { CustomerServiceService } from '../../customer-service.service';
@Component({
  selector: 'app-bookappointment',
  templateUrl: './bookappointment.component.html',
  styleUrls: ['./bookappointment.component.css']
})
export class BookappointmentComponent implements OnInit {
  formNotValid: boolean = false
  formError?: string = ""
  status_arr: string[] = ['booked','completed','approved', 'rejected', 'pending']
  slots : string[] = ['10 AM', "1 PM",'4 PM']
  branches: any
  doctors :any
  labTechnicians :any
  sampleCollectors :any
  nurse :any


  user_data: any
  constructor(private http : CustomerServiceService, private router: Router,
    private httpUser : HttpServiceService) { }

    x:string=''
  bookAppointmentForm: FormGroup = new FormGroup({
    
    username: new FormControl(this.x||'', Validators.required),
    doctor_id: new FormControl(" ", Validators.required),
    branch: new FormControl(null , Validators.required),
    nurse_id: new FormControl(" ", Validators.required),
    lab_technician: new FormControl(" ", Validators.required),
    sample_collector: new FormControl(" ", Validators.required),
    status: new FormControl(" ", Validators.required),
    slot: new FormControl(" ", Validators.required),
  })
    
  ngOnInit(): any {
    this.httpUser.getBranches().subscribe(data => {
      this.branches = data
      console.log(this.branches)})
    this.http.getDoctors().subscribe(data => {
      this.doctors = data
      console.log(this.doctors);
      })
    this.http.getLabTechnician().subscribe(data => {
      this.labTechnicians= data
      console.log(this.labTechnicians);
    })
    this.http.getNurse().subscribe(data => {
      this.nurse = data
      console.log(this.nurse);
    })
    this.http.getSampleCollector().subscribe(data => {
      this.sampleCollectors = data
      console.log(this.sampleCollectors);
    })

      
  }


  bookAppointment(){
    console.log(this.bookAppointmentForm.value);
    this.http.bookAppointment({ 'form': this.bookAppointmentForm.value, 'username': this.bookAppointmentForm.get('username')?.value }).subscribe(data=>{
      console.log(data);
    })
  }
}
