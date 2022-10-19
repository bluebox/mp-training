import { Dialog } from '@angular/cdk/dialog';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PaymentInterface } from 'src/app/interface/payment';
import { UserInterface } from 'src/app/interface/user';
import { BookingService } from 'src/app/services/booking.service';
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
  promocode:FormGroup=new FormGroup({
    promo:new FormControl("")
  })

  constructor(@Inject(MAT_DIALOG_DATA) public data: {price:number,bookingData:any},private router:Router,private pro:PromocodeService,private dialog:Dialog,private book:BookingService,private user:UserdataService) { }

  ngOnInit(): void {
   
  }
  gohome(){
    // this.dialog.open(TicketComponent)
    let payment:PaymentInterface={
      Booking_id:this.data.bookingData.Booking_id,
      PROMOCODE:this.promocode.value,
      T_price:this.data.price,
      Payment_status:'Paid',
      Payment_mode:'Online'
    }
    this.book.postPayment(payment).subscribe(data=>console.log(data))
    this.user.getuser().subscribe(data=>{this.userData=data,this.router.navigate(['booking/ticket',this.userData.User_id])})
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
  this.book.postData(this.data.bookingData).subscribe(data=>console.log("booking updated"))
 
    
  }

}
