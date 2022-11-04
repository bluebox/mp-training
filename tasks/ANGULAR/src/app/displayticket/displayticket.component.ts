import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-displayticket',
  templateUrl: './displayticket.component.html',
  styleUrls: ['./displayticket.component.css']
})
export class DisplayticketComponent implements OnInit {
  tik:any=[]
  status:boolean=true;
  constructor(private service:SharedService) { }

  ngOnInit(): void {
    this.getlatestticket()
  }

isactive()
{
  this.status=!this.status
}

getlatestticket()
{
  this.service.getTicketdetails().subscribe(data=>
    {
      this.tik=data;
    })
}




}
