import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { AppointmentsService } from 'src/app/services/appointments-service/appointments.service';
import { CustomerServiceService } from 'src/app/modules/customer/customer-service.service';
import { HttpService } from 'src/app/services/http-service/http.service';

@Component({
  selector: 'app-book-appointment',
  templateUrl: './book-appointment.component.html',
  styleUrls: ['./book-appointment.component.css']
})
export class BookAppointmentComponent implements OnInit {
  // update
  todayDate: Date = new Date();
  id: any
  appointment: any
  relatedTests: any
  // create
  formNotValid: boolean = false
  formError?: string = ""
  status_arr: string[] = ['booked', 'completed', 'approved', 'rejected', 'pending']
  slots: string[] = ['10 AM', "1 PM", '4 PM']
  labTechnicians: any;
  sampleCollectors: any;
  nurse: any
  isEmployee: boolean = true;
  users: any;
  tests: any;
  branches: any
  doctors: any;
  nurses: any;
  lab_technicians: any;
  sample_collectors: any;
  user: any = window.localStorage.getItem('user')
  user_data: any = JSON.parse(this.user)
  user_info_data: any = window.localStorage.getItem('user_data')
  user_info: any = JSON.parse(this.user_info_data)


  constructor(private http: CustomerServiceService, private router: Router,
    private actRouter: ActivatedRoute,
    private appointment_service: AppointmentsService,
    private httpUser: HttpServiceService, private httpService: HttpService,) {


    this.actRouter.params.subscribe(data => {
      this.id = parseInt(data['id'])
      console.log(this.id);
      
    })

    if (this.id) {
      // console.log(this.id);   
      this.appointment_service.getAppointment(this.id).subscribe({
        next: (resp: any) => {
          this.appointment = resp.appointment
          this.relatedTests = resp.related_tests
          this.bookAppointmentForm.get('user')?.setValue(this.appointment.user)
          this.bookAppointmentForm.get('doctor_id')?.setValue(this.appointment.doctor_id)
          this.bookAppointmentForm.get('branch')?.setValue(this.appointment.branch)
          this.bookAppointmentForm.get('nurse_id')?.setValue(this.appointment.nurse_id)
          this.bookAppointmentForm.get('lab_technician')?.setValue(this.appointment.lab_technician)
          this.bookAppointmentForm.get('sample_collector')?.setValue(this.appointment.sample_collector)
          this.bookAppointmentForm.get('slot')?.setValue(this.appointment.slot)
          this.bookAppointmentForm.get('status')?.setValue(this.appointment.status)
          this.bookAppointmentForm.get('tests')?.setValue(this.appointment.tests)
          this.bookAppointmentForm.get('date')?.setValue(this.appointment.date)

        }
      })
    }
  }

  bookAppointmentForm: FormGroup = new FormGroup({
    user: new FormControl("", Validators.required),
    doctor_id: new FormControl("", Validators.required),
    branch: new FormControl(null, Validators.required),
    nurse_id: new FormControl("", Validators.required),
    lab_technician: new FormControl("", Validators.required),
    sample_collector: new FormControl("", Validators.required),
    status: new FormControl("", Validators.required),
    slot: new FormControl("", Validators.required),
    date: new FormControl("", Validators.required),
    tests: new FormControl("", Validators.required),
  })

  ngOnInit(): any {

    this.httpService.getDetailsForAppointmentBooking().subscribe({
      next: (data: any) => {
        this.users = data.users;
        this.users = JSON.parse(this.users);
        this.branches = data.branches;
        this.tests = data.tests;
        this.doctors = data.doctors;
        this.doctors = JSON.parse(this.doctors);
        this.nurses = data.nurses;
        this.nurses = JSON.parse(this.nurses);
        this.lab_technicians = data.lab_technicians;
        this.lab_technicians = JSON.parse(this.lab_technicians);
        this.sample_collectors = data.sample_collectors;
        this.sample_collectors = JSON.parse(this.sample_collectors);

      },
      error: (err) => {
        console.log(err.data);
      },
    })




  }
  bookAppointment() {
    if (this.id) {
      this.appointment_service.updateAppointment(this.id, this.bookAppointmentForm.value).subscribe(data => {
        console.log(data);
      })
      this.router.navigate(['admin/display-appointments'])
    }
    else {
      this.appointment_service.setAppointment({ 'form': this.bookAppointmentForm.value }).subscribe(data => {
        this.router.navigate(['admin/display-appointments'])
      })
    }
  }
}