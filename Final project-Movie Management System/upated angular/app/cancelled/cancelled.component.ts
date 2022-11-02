import { Component, OnInit } from '@angular/core';
import { BookingService } from '../services/booking.service';
import { UserdataService } from '../services/userdata.service';

@Component({
  selector: 'app-cancelled',
  templateUrl: './cancelled.component.html',
  styleUrls: ['./cancelled.component.css']
})
export class CancelledComponent implements OnInit {
  public can:any
  public available:boolean=false

  constructor(private user:UserdataService,private book:BookingService) { }

  ngOnInit(): void {
   this.user.getuser().subscribe(data=>this.book.cancelHistory(data.User_id).subscribe(data=>{this.can=data;
  if(this.can.length>0){
    this.available=true
  }}))
    
  }

}
