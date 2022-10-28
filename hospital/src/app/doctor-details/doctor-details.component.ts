import { Component, OnInit } from '@angular/core';
import { ServercomunicationService } from '../servercomunication.service';
// import {Doctor} from '../interfaces/doctor';
import { BookingService } from '../booking.service';
import { MatBottomSheetRef } from '@angular/material/bottom-sheet';
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

  ngOnInit(): void {
    // this.getData();
    this.getDocDetails()
    // this.getUserdetails()
    // console.log(this.doctors);
    // console.log(this.getData());
  }
  hide(event: MouseEvent): any{
    this._bottomSheetRef.dismiss();
    event.preventDefault();
 }

//  getUserdetails(){
//   this.api.getuser().subscribe(
//     (res:any)=>{
//       console.log(res)
//     },
//     (error:any)=>{
//       console.log(error);
//     }
//   )
// }
  getDocDetails(): any{
    this.doctors=this.book.getDocDetails()
    console.log(this.doctors);
  }
  getData()
  {
    this.api.getADoctor(this.id).subscribe(
      (data)=>{
        console.log(data);
        this.doctor=data;
        console.log(this.doctor.id)
      },
      error=>{

    console.log(error);

    // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
    // console.log(this.patients)
  })
  }
}
