import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfirmComponent } from '../booking/components/confirm/confirm.component';
import { BookingService } from '../services/booking.service';
import { UserdataService } from '../services/userdata.service';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { DatePipe } from '@angular/common';

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
  public myDate:any
  public cancel:boolean=true
  public history:any
     
  

  constructor(public user:UserdataService,private book:BookingService,private dialog:MatDialog,private router:Router,private datePipe: DatePipe) { 
    this.myDate=this.datePipe.transform((new Date),'yyyy-MM-dd')
  }

  ngOnInit(): void {
      this.book.getBooking(this.user.user.User_id).subscribe(data=>{this.history=data
        if(this.history[0].length!=0){
          this.ticket=this.history[0]
          this.available=true
        }
        if(this.history[1].length!=0){
          this.can=this.history[1]
          this.available2=true
        }
        if(this.history[3].length!=0){
          this.up=this.history[3]
          this.available4=true
        }
        this.lastticket=this.history[2]
        this.available3=true
        if(this.lastticket.Date<this.myDate){
          this.cancel=false
        }
      }
        )
      
    }
  Confirm(bid:number){
    this.dialog.open(ConfirmComponent,{data:{'id':this.user.user.User_id,'Booking_id':bid}})
  }

  htmltoPDF(link:string)
  {
    let el=document.getElementById("ticket1")
    if(el){
      html2canvas(el).then(canvas => {
        var doc = new jsPDF("l", "px", "a4");
        // var width = doc.internal.pageSize.getWidth();
        // var height = doc.internal.pageSize.getHeight();
        console.log(link)
        var imgData  = canvas.toDataURL('link/png', 1.0);
        doc.addImage(imgData, 'PNG',100,100,canvas.width,canvas.height);
        doc.save('converteddoc.pdf');

  
    });
  
  }
  }
  

}
