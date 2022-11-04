import { Component, HostListener, Input, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {

  flightform:any=FormGroup
  flight_id!:number
  destination:string='';
  flightList:any;
  fullflightList:any;

  constructor(private service:SharedService) { }
  @Input() tik:any;
  ngOnInit(): void {
    this.flightform = new FormGroup({
      flight_name: new FormControl(''),
      destination: new FormControl(''),

    });
    this.refresh_list()
  }


  onsubmit(val:any)
  {
     this.service.obtainFlightlist(val).subscribe(data=>
      {
        let res = JSON.stringify(data)
        let resObj = JSON.parse(res)
        this.flightList=resObj
      });
   this.tik=val.flight_id;
  }

 refresh_list()
 {
  this.service.obtainfullFlightlist().subscribe((data:any)=>

  {
    this.fullflightList=data;
  })
 }







}
