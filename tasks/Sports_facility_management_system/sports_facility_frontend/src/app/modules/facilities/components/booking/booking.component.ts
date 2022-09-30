import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Data } from '@angular/router';
import { Facility } from 'src/interfaces/facility';
import { Slots } from 'src/interfaces/slot';
import { Sports } from 'src/interfaces/sport';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css'],
})
export class BookingComponent implements OnInit {
  fid: string = '';
  sid: string = '1';
  idd: string = '';
  facility!: Facility;
  sports!: Sports[];
  sportid!: number;
  datesarray: any[] = [];
  slots: Slots[] = [];
  slotsid: string[] = [];
  bookingForm !: FormGroup;

  constructor(
    private arouter: ActivatedRoute,
    private facilityService: FacilityService
  ) {}
  // booking! :FormGroup
  ngOnInit(): void {
      this.bookingForm=new FormGroup({
       sid : new FormControl('' ),

    })
    this.arouter.params.subscribe((data) => {
      this.fid = data['id'];
      console.log(data);
    });

    this.facilityService
      .getFacility(this.fid)
      .subscribe((data) => (this.facility = data));

    this.facilityService
      .getSportsInFacility(this.fid)
      .subscribe((data) => (this.sports = data));

    this.facilityService
      .getSlotsInSportFacility(this.fid, this.sid)
      .subscribe((data) => (this.slots = data));

    this.dates();
  }
  ngDoCheck(): void {
    console.log(this.sid, this.slotsid);
  }

  selectSport(sportid: number): void {
    this.sportid = sportid;
  }

  dates(): void {
    for (let i = 0; i < 5; i++) {
      var simpilifiedDate: any[] = [];
      var date = new Date();
      date.setDate(date.getDate() + i);
      const month = date.toLocaleString('default', { month: 'long' });
      const day = date.getDate();
      simpilifiedDate.push(day + ',' + month);
      const weekday = date.toLocaleString('default', { weekday: 'long' });
      simpilifiedDate.push(weekday);

      this.datesarray.push(simpilifiedDate);
    }
  }
  getSportsId(id: number): void {
    this.sid = String(id);
  }

  getslotid(id: number): void {
    this.idd = String(id);
    const index = this.slotsid.indexOf(this.idd);
    if (index !== -1) {
      this.slotsid.splice(index,1);
    } else {
      this.slotsid.push(this.idd);
    }
  }

  submitBookingForm(): void {}
}
