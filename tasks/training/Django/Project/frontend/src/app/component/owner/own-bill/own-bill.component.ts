import { GeneralService } from './../../../general.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-own-bill',
  templateUrl: './own-bill.component.html',
  styleUrls: ['./own-bill.component.css'],
})
export class OwnBillComponent implements OnInit {
  data: any;
  trip: any;

  constructor(
    private service: GeneralService,
    private activateRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.service.bill.subscribe((data) => {
      this.data = data;

    });

    this.service.trip.subscribe((res) => {
      this.trip = res;
    });

    this.activateRoute.params.subscribe((data) => {
      if (data['id']) {
        this.service.getBill(data['id']).subscribe((data) => {
          // console.log(data);
        });

        this.service.getTrip(data['id']).subscribe((data) => {
          console.log(data);
        });
      }
    });
  }
}
