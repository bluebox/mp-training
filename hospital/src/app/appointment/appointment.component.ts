import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BookingService } from '../booking.service';
import { ServercomunicationService } from '../servercomunication.service';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material/bottom-sheet;
import { DoctorDetailsComponent } from '../doctor-details/doctor-details.component';
@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css'],
  providers:[ServercomunicationService]
})
export class AppointmentComponent implements OnInit {
  form_appointment: FormGroup = new FormGroup({});
  doctor: any;
  doctors:any=[
    {name: "ayan", dob:"1995"},
    {name: "ayan1", dob:"1995"},
    {name: "ayan2", dob:"1995"},
    {name: "ayan3", dob:"1995"},
    {name: "ayan4", dob:"1995"}
  ]
  slot: any;
  constructor(private bottomSheet: MatBottomSheet,private fb: FormBuilder,private api:ServercomunicationService,private book:BookingService) { }
  openBottomSheet(): void {
    this.bottomSheet.open(DoctorDetailsComponent);
  }
  ngOnInit(): void {
    this.get_Doc_Data();
    this.get_Slot_Data();
  this.book.setDocData(this.doctors);

    console.log(this.get_Doc_Data());
    console.log(this.get_Slot_Data());
  }
  get_Doc_Data()
{
  this.api.getAllDoctor().subscribe(
    (data)=>{
      console.log(data);
      this.doctor=data;
      console.log(this.doctor[0].d_id)
      // this.book.setDocData(data)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}

getData(){
this.book.getDocDetails()
}
get_Slot_Data()
{
  this.api.getAllSlot().subscribe(
    (data)=>{
      console.log(data);
      this.slot=data;
      console.log(this.slot[0].slot_id)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}

}
