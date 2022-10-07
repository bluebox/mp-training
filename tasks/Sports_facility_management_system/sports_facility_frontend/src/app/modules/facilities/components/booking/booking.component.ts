import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Data, Router } from '@angular/router';
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
  fid: string = ''; //facilityid
  idd!: number;
  facility!: Facility; 
  sports!: Sports[];
  equipments!: any[];
  sportid!: number;
  date: string = '';
  fsid!: number;
  datesarray: any[] = [];
  slots: Slots[] = [];
  booked_slots: Slots[] = [];
  booked_slots_ids: number[] = [];
  slotsid: number[] = [];
  equipments_booked: any[] = [];
  equipments_quantity: any[] = [];
  bookingForm!: FormGroup;
  user_id: number = 1;

  bookingmsg: any;
  fsdetails: any;
  cost_per_slot: any;
  total_cost_for_slots!: number;
  total_cost_for_equipments: number = 0;




  constructor(
    private router: Router,
    private arouter: ActivatedRoute,
    private facilityService: FacilityService
  ) {}
  // booking! :FormGroup
  ngOnInit(): void {
    this.bookingForm = new FormGroup({
      sid: new FormControl(''),
    });
    this.arouter.params.subscribe((data) => {
      this.fid = data['id'];
      console.log(data);
    });

    this.facilityService
      .getFacility(this.fid)
      .subscribe((data) => (this.facility = data));

    this.facilityService.getSportsInFacility(this.fid).subscribe((data) => {
      (this.sports = data), console.log(data);
    });

    this.dates();
  }
  ngDoCheck(): void {}
  ngOnDestroy(): void {}

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
    this.sportid = id;

    this.facilityService
      .getFacilitySportId(this.fid, this.sportid)
      .subscribe((data) => {
        this.fsdetails = data;
        this.fsid = this.fsdetails.facility_sport_id;
        console.log(this.fsid);
        this.cost_per_slot = this.fsdetails.cost_per_slot;
        console.log(this.cost_per_slot);
      });

    this.facilityService.getEquipments(this.sportid).subscribe((data) => {
      // (this.equipments = data);
      let array: any[] = [];
      data.map((ele: any) => {
        array.push({ equipment: ele, count: 0, cost: 0 });
      });
      this.equipments = array;

      console.log(this.equipments);
    });
    if (this.date) {
      this.facilityService
        .getSlotsInSportFacility(this.fid, this.sportid)
        .subscribe((data) => (this.slots = data));
      this.facilityService
        .getBookedSlots(this.fsid, this.date)
        .subscribe((data) => {
          (this.booked_slots = data), this.getSlotids();
        });
    }
  }
  getDate(date: string): void {
    this.date = date;
    console.log(this.date);
    if (this.sportid) {
      this.facilityService
        .getSlotsInSportFacility(this.fid, this.sportid)
        .subscribe((data) => (this.slots = data));
      this.facilityService
        .getBookedSlots(this.fsid, this.date)
        .subscribe((data) => {
          (this.booked_slots = data), this.getSlotids();
        });
    }
  }
  getslotid(id: number): void {
    this.idd = id;
    const index = this.slotsid.indexOf(this.idd);
    if (index !== -1) {
      this.slotsid.splice(index, 1);
    } else {
      this.slotsid.push(this.idd);
    }
    console.log(this.slotsid);
    this.total_cost_for_slots = this.cost_per_slot * this.slotsid.length;
    this.totalCostForEquipments()
  }

  getSlotids(): void {
    this.booked_slots_ids = [];
    for (let i = 0; i < this.booked_slots.length; i++) {
      this.booked_slots_ids.push(this.booked_slots[i].slot_id);
    }
    ;
  }
  increasebtn(id: number): void {
    this.equipments[id].count += 1;
    this.costForEquipments(id);
    // this.totalCostForEquipments()
  }

  reducebtn(id: number): void {
    if (this.equipments[id].count > 0) {
      this.equipments[id].count -= 1;
      this.costForEquipments(id);
      // this.totalCostForEquipments()
    }
  }

  EquipmentsBooked(): void {
    this.equipments.forEach((item) => {
      if (item.count > 0) {
        this.equipments_booked.push(item.equipment.equip_name);
        this.equipments_quantity.push(item.count);
      }
    });
  }

  costForEquipments(id: number): void {
    this.equipments[id].cost =
      this.equipments[id].equipment.rent_per_slot * this.equipments[id].count;
    this.totalCostForEquipments();
  }

  totalCostForEquipments(): void {
    this.total_cost_for_equipments = 0;
    this.equipments.forEach((item) => {
      this.total_cost_for_equipments +=
        item.cost * this.slotsid.length;
    });
    console.log(this.total_cost_for_equipments);
  }

  submitBookingForm(): void {
    this.EquipmentsBooked();

    const obj = {
      user_id: this.user_id,
      facility_sport_id: this.fsid,
      date: this.date,
      slots_id: this.slotsid,
      equipments_booked: this.equipments_booked,
      equipments_quantity: this.equipments_quantity,
    };
    const costobj = {
      cost_for_slot:this.total_cost_for_slots,
      cost_for_equipments:this.total_cost_for_equipments,
      total_cost:this.total_cost_for_slots+this.total_cost_for_equipments,

    }
    console.log(obj);
    this.facilityService.postBookingData(obj).subscribe((data) => {
      (this.bookingmsg = data), console.log(data);
      sessionStorage.setItem('bookingobj', JSON.stringify(obj));
      sessionStorage.setItem('totalcost', JSON.stringify(costobj));
      this.router.navigate(['facilities/paymentpage']);
    });
    
    
  }
}
