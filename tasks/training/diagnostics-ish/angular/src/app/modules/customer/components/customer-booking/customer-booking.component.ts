import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { SubjectServiceService } from 'src/app/services/subject-service/subject-service.service';
import { CustomerServiceService } from '../../customer-service.service';
@Component({
  selector: 'app-customer-booking',
  templateUrl: './customer-booking.component.html',
  styleUrls: ['./customer-booking.component.css']
})
export class CustomerBookingComponent implements OnInit {
  slots: string[] = ['10 AM', "1 PM", '4 PM']
  branches: any
  errorMessage : boolean = false
  data : any
  todayDate: Date = new Date();
  tests : any

  constructor(private http: CustomerServiceService, private router: Router, private httpUser: HttpServiceService , 
    private subjectService : SubjectServiceService) { }
  bookAppointmentForm: FormGroup = new FormGroup({
    branch: new FormControl("", Validators.required),
    slot: new FormControl("", Validators.required),
    date: new FormControl("", Validators.required),
    tests: new FormControl("", Validators.required),
  })
  ngOnInit(): void {
    this.httpUser.getBranches().subscribe(data => {
      this.branches = data
      console.log(this.branches)
    })
    this.httpUser.getTests().subscribe({
      next:(data:any)=>{
          this.tests = data['tests']
          console.log(this.tests)
      }
    } )

  }
  bookAppointment(){
    console.log(this.bookAppointmentForm.get("date")?.value);
    
    if(this.bookAppointmentForm.valid){
      this.data = this.bookAppointmentForm.value
      this.subjectService.userTypeIdSubject.subscribe(customer_id =>{
        this.data['user']= customer_id
      })
      this.http.bookAppointment({ 'form': this.data, 'username': "customer" }).subscribe(data => {
        console.log(data);
        this.router.navigate([''])
      })
    }
    else {
      this.errorMessage = true
    }
  }

}
