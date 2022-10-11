import { Component, OnInit } from '@angular/core';
import { ServercomunicationService } from '../servercomunication.service';
import {Doctor} from '../interfaces/doctor';
import { BookingService } from '../booking.service';
import { MatBottomSheetRef } from '@angular/material/bottom-sheet';
import { Appointment } from '../interfaces/appointment';
@Component({
  selector: 'app-doctor-details',
  templateUrl: './doctor-details.component.html',
  styleUrls: ['./doctor-details.component.css'],
  providers:[ServercomunicationService]
})
export class DoctorDetailsComponent implements OnInit {
  doctor: any;
  doctors:any;
  id:any;
  constructor(private _bottomSheetRef: MatBottomSheetRef<DoctorDetailsComponent>,private api:ServercomunicationService,private book: BookingService ) {}

  openLink(event: MouseEvent): void {
    this._bottomSheetRef.dismiss();
    event.preventDefault();
  }
  ngOnInit(): void {
    this.getData();

    console.log(this.doctors);
    console.log(this.getData());
  }

  getDocDetails(){
    this.doctors=this.book.getDocDetails()
    console.log(this.doctors);

  }
  getData()
  {
    this.api.getADoctor(this.id).subscribe(
      (data)=>{
        console.log(data);
        this.doctor=data;
        console.log(this.doctor.d_id)
      },
      error=>{

    console.log(error);

    // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
    // console.log(this.patients)
  })
  }
}
