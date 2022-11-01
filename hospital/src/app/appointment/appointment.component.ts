import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookingService } from '../booking.service';
import { ServercomunicationService } from '../servercomunication.service';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material/bottom-sheet'
import { DoctorDetailsComponent } from '../doctor-details/doctor-details.component';
import { DatePipe } from '@angular/common';
export interface Appointment{
  // id:string;
  doctor:any;
  patients:any;
  slot:any;
}

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css'],
  providers:[ServercomunicationService,DatePipe],
})
export class AppointmentComponent implements OnInit {
  form_appointment: FormGroup = new FormGroup({});
  doctor: any;
  date:any;
  user:any;
  appointed:any;
  temp:any;
  slot: any;
  selectedDoctorDetails: any;
  selectedSlotDetails: any;
  patient: any;
  constructor(private datePipe:DatePipe,private _bottomSheet: MatBottomSheet,private fb: FormBuilder,private api:ServercomunicationService,private book:BookingService) { }
  openBottomSheet(): void {
    this._bottomSheet.open(DoctorDetailsComponent);
  }
  ngOnInit(): void {

    this.user=this.book.getUserDetails()
    this.form_appointment = this.fb.group({
      doctor: [null, [Validators.required]],
      slot: [null],
      dates: [null, [Validators.required]],
  });
    this.get_Doc_Data();
  //  this. getData();
  this.book.setDocData(this.doctor);
  // this.book.setSlotData(this.slot);
  this.getUserdetails();
  console.log(this.user);

  }
  get_Doc_Data()
{
  this.api.getAllDoctor().subscribe(
    (data)=>{
      this.doctor=data;
      console.log(this.doctor);
      this.book.setDocData(data);
    },
    error=>{

  console.log(error);
})
}

getUserdetails(){
  this.api.getuser().subscribe(
    (res:any)=>{
      this.user=res.email;
      console.log(this.user);
     this.getPatientData();
    },
    (error:any)=>{
      console.log(error);
      console.log("userdetails");
    }
  )
}
getPatientData()
{
  this.api.getAPatient(this.user.email).subscribe(
    (data: any)=>{
      console.log(data);
      this.patient=data[0].id;
      console.log(this.patient)
      this.book.setDocData(data)
    },
    (    error: any)=>{

  console.log(error);
})
}
saveDetails(form_appointment:any){
  console.log(this.form_appointment);
  let app={
    "slot":this.selectedSlotDetails,
    "patient":this.patient,
  }
  console.log('detail1');
  console.log(app);
// this.temp=this.getADocData(this.doc: any);
const data=JSON.stringify(app)
this.api.register_appointment(app).subscribe(
  (data)=>{
    console.log(data);
    this.doctor=data;

  },
  error=>{

console.log(error);
})
}

getCellData(id:any){
  this.selectedDoctorDetails=id
  console.log(id,this.selectedDoctorDetails)
}
getSlotCellData(id:any){
  this.selectedSlotDetails=id
  console.log(id,this.selectedSlotDetails)
}
get_Slot_Data()
{
  let date=this.datePipe.transform(this.form_appointment.value.dates, 'yyyy-MM-dd')

  this.api.getAllSlot(this.selectedDoctorDetails,date).subscribe(
    (data)=>{
      console.log(this.selectedDoctorDetails,date)
      console.log(data);
      this.slot=data;
      console.log(this.slot[0].slots)
    },
    error=>{
  console.log(error);
})
console.log('detail2')
}
}
