import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-show-ticket',
  templateUrl: './show-ticket.component.html',
  styleUrls: ['./show-ticket.component.css']
})
export class ShowTicketComponent implements OnInit {
  tik:any=[]

  // this.count=this.count+1

  constructor(private service:SharedService) { }

  ngOnInit(): void {

  }
 ;


 




}
