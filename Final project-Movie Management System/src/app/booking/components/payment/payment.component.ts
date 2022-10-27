import { Dialog } from '@angular/cdk/dialog';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { HallInterface } from 'src/app/interface/hall';
import { PaymentInterface } from 'src/app/interface/payment';
import { UserInterface } from 'src/app/interface/user';
import { BookingService } from 'src/app/services/booking.service';
import { HallDataService } from 'src/app/services/hall-data.service';
import { PromocodeService } from 'src/app/services/promocode.service';
import { UserdataService } from 'src/app/services/userdata.service';
import { TicketComponent } from '../ticket/ticket.component';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  public codes:any
  public discount:number=0
  public userData!:UserInterface
  public sdate!:Date
  public update:any
  promocode:FormGroup=new FormGroup({
    promo:new FormControl("")
  })

  constructor(@Inject(MAT_DIALOG_DATA) public data: {price:number,bookingData:any},private router:Router,private pro:PromocodeService,private dialog:Dialog,private book:BookingService,private user:UserdataService,private hall:HallDataService) { }

  ngOnInit(): void {
    this.sdate=this.hall.getDate()
   console.log("BookingDta:",this.data.bookingData)
  }
  gohome(){
    // this.dialog.open(TicketComponent)
    let payment={
      'Date':this.data.bookingData.Date,
      'Selected_seats':this.data.bookingData.Selected_seats,
      'Booking_status':'Booked',
      'T_price':this.data.bookingData.T_price,
      'Payment_Status':'Paid',
      'Payment_mode':'Online',
      'User_id':this.data.bookingData.User_id,
      'Movie_id':this.data.bookingData.Movie_id,
      'Theatre_id':this.data.bookingData.Theatre_id,
      'Hall_id':this.data.bookingData.Hall_id
    }
     this.hall.SingleHall(this.data.bookingData.Hall_id,this.sdate).subscribe((data=>{this.update=data

    this.update.T_No_Of_Seats= this.update.T_No_Of_Seats-(this.data.bookingData.T_price/100)
    console.log("data being updated in payment",this.update)
    this.hall.DeductSeat(this.data.bookingData.Hall_id,this.sdate,this.update).subscribe(data=>console.log(data))}))
    console.log(payment)
    // this.book.postPayment(payment).subscribe(data=>console.log(data))
    this.book.postData(payment).subscribe(data=>console.log("booking updated"))
    this.router.navigate(['booking/ticket',this.user.user.User_id])
    this.dialog.closeAll()
  }
  submit(){
    let p=this.promocode.value
    console.log(p)
    this.pro.GetCodes().subscribe(data=>{this.codes=data
      console.log(this.codes.length)
      for(let i=0;i<this.codes.length;i++){
        console.log(p,this.codes[i].PROMOCODE)
        if(p.promo===this.codes[i].PROMOCODE){
          this.discount=this.codes[i].Discount
          console.log(this.discount)
        }
        else{
          console.log("not equal")
        }
      }
    }
  ) 
  }
  nopay(){
    this.router.navigate([''])
  }

}
