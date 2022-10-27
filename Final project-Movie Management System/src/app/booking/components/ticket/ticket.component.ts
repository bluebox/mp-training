import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BookingInterface } from 'src/app/interface/booking';
import { MovieInterface } from 'src/app/interface/movie';
import { UserInterface } from 'src/app/interface/user';
import { BookingService } from 'src/app/services/booking.service';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { UserdataService } from 'src/app/services/userdata.service';
import { ConfirmComponent } from '../confirm/confirm.component';

MoviedataService
@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {
  public currentUser!:UserInterface
  public ticket:any
  public available:boolean=false

  constructor(private book:BookingService,private user:UserdataService,private router:Router,private dialog:MatDialog) { }

  ngOnInit(): void {
  this.book.getTicket(this.user.user.User_id).subscribe(data=>{this.ticket=data;
        this.available=true})
  }
  GoHome(){
    this.router.navigate([''])
  }
  Confirm(){
    this.dialog.open(ConfirmComponent,{data:{'id':this.currentUser.User_id}})
  }

}
