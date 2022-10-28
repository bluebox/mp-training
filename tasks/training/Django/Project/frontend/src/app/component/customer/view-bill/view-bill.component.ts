import { ActivatedRoute, Router } from '@angular/router';
import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-bill',
  templateUrl: './view-bill.component.html',
  styleUrls: ['./view-bill.component.css']
})
export class ViewBillComponent implements OnInit {
  data : any
  trip : any


  constructor(private service : GeneralService, private route : Router, private activatedRoute : ActivatedRoute) { }

  ngOnInit(): void {
    this.service.bill.subscribe(data => {
      (this.data = data)

    })
    this.service.trip.subscribe(res => {
      (this.trip = res)
    })


    this.activatedRoute.params.subscribe((data)=>{
      if(data){
        this.service.getBill(data['id']).subscribe((data)=>{
          console.log(data)
        })

        this.service.getTrip(data['id']).subscribe((data)=>{
          console.log(data)
        })
      }
    })
  }



}

