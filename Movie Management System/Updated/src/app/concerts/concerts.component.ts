import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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
  public currentUser!:UserInterface
  public ticketData!:BookingInterface[]
  public ticketMovie!:MovieInterface

  constructor(private user:UserdataService,private book:BookingService,private dialog:MatDialog) { }

  ngOnInit(): void {
    this.user.getuser().subscribe(data=>{this.currentUser=data,
      console.log(this.currentUser),
      this.book.getBookingHistory(this.currentUser.User_id).subscribe(data=>{this.ticketData=data})})
  }
  
  cancel(){
    this.dialog.open(ConfirmComponent)
  }

}
