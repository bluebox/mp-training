import { GeneralService } from 'src/app/general.service';
import { compileNgModule } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  response: any;
  vehicle! : FormGroup
  vehicle_data : any = window.sessionStorage.getItem('vehicle');
  vehicle_obj = JSON.parse(this.vehicle_data)

  customer_obj : any = window.sessionStorage.getItem('customer_id');
  customer = JSON.parse(this.customer_obj);

  constructor(private service : GeneralService, private router : Router) {
    console.log(this.vehicle_obj.vehicle_no)
    console.log(this.vehicle_obj.owner_id)
    this

  }
  ngOnInit(): void {
    this.service.getOwner(this.vehicle_obj.owner_id).subscribe(data=> {(this.response=data),
      console.log(this.response)})

      if(this.vehicle_obj){
        this.vehicle = new FormGroup({
          pickup_time_date: new FormControl('', Validators.required)
        })
      }

  }


  bookSelectedVehicle(){

    // let veh = {...this.vehicle_obj, }
    // console.log(this.vehicle.value['pickup_time_date'])
    // console.log(this.vehicle_obj)
    let veh = {
      "vehicle_no": this.vehicle_obj.vehicle_no,
      "customer_id": this.customer_obj.customer_id,
      "owner_id": this.vehicle_obj.owner_id,
      'pickup_time_date': this.vehicle.value['pickup_time_date']
    }
    console.log(veh)

    this.service.bookVehicle(veh).subscribe(data=>console.log(data) )
    // this.vehicle = new FormGroup({
    //   "vehicle_no": this.vehicle_obj.vehicle_no,
    //   "customer_id": this.customer_obj.customer_id,
    //   "owner_id": this.vehicle_obj.owner_id,
    //   'pickup_time_date': new FormControl('', Validators.required)
    // })

  }

}
