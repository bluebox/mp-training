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
  constructor(private service : GeneralService) { }

  ngOnInit(): void {
    this.service.bill.subscribe(data => {
      (this.data = data)

    })
    this.service.trip.subscribe(res => {
      (this.trip = res)
    })
    console.log(this.data)
    console.log(this.trip)

  }

}
