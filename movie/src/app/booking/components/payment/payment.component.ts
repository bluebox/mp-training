import { Dialog } from '@angular/cdk/dialog';
import { ThisReceiver } from '@angular/compiler';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PromocodeService } from 'src/app/services/promocode.service';
import { TicketComponent } from '../ticket/ticket.component';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  public codes:any
  public discount:number=0
  promocode:FormGroup=new FormGroup({
    promo:new FormControl("")
  })

  constructor(@Inject(MAT_DIALOG_DATA) public data: {price:number},private router:Router,private pro:PromocodeService,private dialog:Dialog) { }

  ngOnInit(): void {
  }
  gohome(){
    // this.dialog.open(TicketComponent)
    this.router.navigate([''])
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

}
