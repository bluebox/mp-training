import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookingService } from '../booking.service';
import { ServercomunicationService } from '../servercomunication.service';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material/bottom-sheet'
import { DoctorDetailsComponent } from '../doctor-details/doctor-details.component';
import { DatePipe, NgIf } from '@angular/common';
import { Slot } from '../interfaces/slots';
@Component({
  selector: 'app-slot-booking',
  templateUrl: './slot-booking.component.html',
  styleUrls: ['./slot-booking.component.css']
})

export class SlotBookingComponent implements OnInit {
  isvalid: any;
 form_slot: FormGroup = new FormGroup({});
 slot:any;
  user!: any;
  slotss:Slot[]=[];
  data: any;
  docId: any;
  doctor: any;

  constructor(private fb: FormBuilder,private datePipe:DatePipe,private book:BookingService, private api:ServercomunicationService) { }

  ngOnInit(): void {
    this.getUserdetail();
    // NgIf (isvalid==true)
    console.log(this.user?.email)
    this.form_slot = this.fb.group({
      // doctor:[null],
      startTime:[null, [Validators.required]],
      endTime:[null, [Validators.required]],
      dates: [null, [Validators.required]],
      // status:[null],
  });

  }

  saveDetails(){
  let date=this.datePipe.transform(this.form_slot.value.dates, 'yyyy-MM-dd');
  // let stime=this.datePipe.transform(this.form_slot.value.starTime,'hh:mm')
  // console.log(this.form_slot.value);
  let app={
    "date":date,
    "doctor":this.doctor,
    "satus":"A",
    "startTime":this.form_slot.value.startTime,
    "endTime":this.form_slot.value.endTime,
  }
  console.log(app);
  const data=JSON.stringify(app)
  this.api.register_slot(data).subscribe(
    (res)=>{
      console.log(res);
      this.data=res;

    },
    error=>{

  console.log(error);
  })
  }
  getUserdetail(){
    this.api.getuser().subscribe(
      (res:any)=>{
        this.user=res.email;
        console.log("user");
        console.log(this.user);
        this.isvalid=true
        this.getdoctorId();
      },
      (error:any)=>{
        console.log(error);
        console.log("not log in");

      }

    )

  }
  getdoctorId(){
    this.api.getADoctor(this.user).subscribe(
      (data: any)=>{
        console.log(data);
        this.doctor=data[0].id;
        console.log("doctor");

        console.log(this.doctor)
        // this.book.setDocData(data)
      },
      (    error: any)=>{

    console.log(error);
  })
  }
}
