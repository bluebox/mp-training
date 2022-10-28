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
  date:string|any;
  slotss:string;
  status:any
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


  // doctors:any=[
  //   {name: "ayan", dob:"1995"},
  //   {name: "ayan1", dob:"1995"},
  //   {name: "ayan2", dob:"1995"},
  //   {name: "ayan3", dob:"1995"},
  //   {name: "ayan4", dob:"1995"}
  // ]
  // slots:any= [
  //   {id:'A',name:'8:30-8:45'},
  //   {id:'B',name:'8:45-9:45'},
  //   {id:'C',name:'9:45-10:00'},
  //   {id:'D',name:'10:30-10:45'},
  //   {id:'E',name:'11:30-11:45'},
  //   {id:'F',name:'12:30-12:45'},
  //   {id:'G',name:'13:30-13:45'},
  // ]
    slot: any;
  selectedDoctorDetails: any;
  constructor(private datePipe:DatePipe,private _bottomSheet: MatBottomSheet,private fb: FormBuilder,private api:ServercomunicationService,private book:BookingService) { }
  openBottomSheet(): void {
    this._bottomSheet.open(DoctorDetailsComponent);
  }
  ngOnInit(): void {
    this.get_Slot_Data()
    this.form_appointment = this.fb.group({
      doctor: [null, [Validators.required]],
      slot: [null],
      dates: [null, [Validators.required]],
  });
    this.get_Doc_Data();
    // this.get_Slot_Data();
  this.book.setDocData(this.doctor);
  this.book.setSlotData(this.slot);
  this.user=this.book.getUserDetails()
  console.log(this.book.getUserDetails().email);
   this.getUserdetails()

    // console.log(this.get_Doc_Data());
    // console.log(this.get_Slot_Data());
  }
  get_Doc_Data()
{
  this.api.getAllDoctor().subscribe(
    (data)=>{
      console.log(data);
      this.doctor=data;
      console.log(this.doctor)
      this.book.setDocData(data)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
getADocData(doc: any)
{
  this.api.getADoctor(name).subscribe(
    (data: any)=>{
      console.log(data);
      this.doctor=data;
      console.log(this.doctor)
      this.book.setDocData(data)
    },
    (    error: any)=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
getUserdetails(){
  this.api.getuser().subscribe(
    (res:any)=>{
      console.log(res);
    },
    (error:any)=>{
      console.log(error);
    }
  )
}
getData(){
this.book.getDocDetails()
}
get_Slot_Data()
{
  let date=this.datePipe.transform(this.form_appointment.value.dates, 'yyyy-MM-dd')

  this.api.getAllSlot(this.form_appointment.value.doctor,date).subscribe(

    (data)=>{
      console.log(this.form_appointment.value.doctor,this.form_appointment.value.dates)
      console.log(data);
      this.slot=data;
      console.log(this.slot[0].slotss)
    },
    error=>{
  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
saveDetails(){
  console.log(this.form_appointment);
  // this.appointed=this.form_appointment.value;
  // this.appointed.patient=this.user.id;
console.log(this.appointed);
// this.temp=this.getADocData(this.doc: any);
const data=JSON.stringify(this.appointed)
this.api.register_appointment(data).subscribe(


  (data)=>{
    console.log(data);
    this.doctor=data;
  this.get_Slot_Data()

    // console.log(this.doctor)



  },
  error=>{

console.log(error);

// this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
// console.log(this.patients)
})
}

getCellData(id:any){
  this.selectedDoctorDetails=id
  console.log(id,this.selectedDoctorDetails)
}
}
