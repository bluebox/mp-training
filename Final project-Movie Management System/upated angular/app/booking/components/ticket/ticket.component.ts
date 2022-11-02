import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserInterface } from 'src/app/interface/user';
import { BookingService } from 'src/app/services/booking.service';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { UserdataService } from 'src/app/services/userdata.service';
import { ConfirmComponent } from '../confirm/confirm.component';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';


MoviedataService
@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {
  public currentUser!:UserInterface
  public ticket!:any
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
  
  htmltoPDF()
{
  let el=document.getElementById("ticket1")
  if(el){
    html2canvas(el).then(canvas => {
      var doc = new jsPDF("l", "px", "a4");
      // var width = doc.internal.pageSize.getWidth();
      // var height = doc.internal.pageSize.getHeight();
      var imgData  = canvas.toDataURL("this.ticket.Movie_poster/jpg", 1.0);
      doc.addImage(imgData,'JPEG',10,10,canvas.width,canvas.height);
      doc.save('converteddoc.pdf');

  });

}
}
}
