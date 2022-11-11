import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Location } from '@angular/common';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  constructor(private dialog:MatDialog,private location:Location,private book:BookingService,@Inject(MAT_DIALOG_DATA) public data: {id: number,Booking_id:number}) { }

  ngOnInit(): void {
  }
  exit(){
    this.dialog.closeAll()
  }
  cancel(){
    // console.log(this.data.id)
    this.book.cancelBooking(this.data.id,this.data.Booking_id).subscribe(data=>console.log(data),(err)=>{})
    this.dialog.closeAll()
    
  }

}
