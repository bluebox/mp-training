import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfirmComponent } from '../booking/components/confirm/confirm.component';
import { BookingInterface } from '../interface/booking';
import { MovieInterface } from '../interface/movie';
import { UserInterface } from '../interface/user';
import { BookingService } from '../services/booking.service';
import { MoviedataService } from '../services/moviedata.service';
import { UserdataService } from '../services/userdata.service';
@Component({
  selector: 'app-concerts',
  templateUrl: './concerts.component.html',
  styleUrls: ['./concerts.component.css']
})
export class ConcertsComponent implements OnInit {
  public ticket:any
  public available:boolean=false
  public can:any
  public up:any
  public available2:boolean=false
  public available3:boolean=false
  public available4:boolean=false
  public lastticket:any

  

  constructor(public user:UserdataService,private book:BookingService,private dialog:MatDialog,private router:Router) { }

  ngOnInit(): void {
      this.book.getBookingHistory(this.user.user.User_id,'previous').subscribe(data=>{this.ticket=data; 
      if(this.ticket.length>0){
        this.available=true
      }})
      this.getCancelHistory()
     this.getBookingHistory()
      this.getTicket()

    }

  getCancelHistory(){
    this.user.getuser().subscribe(data=>this.book.cancelHistory(data.User_id).subscribe(data=>{this.can=data;
        
      if(this.can.length>0){
        this.available2=true
        
      }}))
  }
  getBookingHistory(){
    this.book.getBookingHistory(this.user.user.User_id,'upcoming').subscribe(data=>{this.up=data, console.log(this.up);
      if(this.up.length>0){
        this.available4=true
        }})
  }
  getTicket(){
    this.book.getTicket(this.user.user.User_id).subscribe(data=>{this.lastticket=data;
      this.available=true
    console.log("ticket",this.lastticket)})
  }

  cancel(){
    this.dialog.open(ConfirmComponent)
  }

  // cancelTicket(){
  //   this.router.navigate(['booking/ticket/',this.user.user.User_id])
  // }

  Confirm(bid:number){
    this.dialog.open(ConfirmComponent,{data:{'id':this.user.user.User_id,'Booking_id':bid}})
  }

}
