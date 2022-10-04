import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-vehicles-register',
  templateUrl: './vehicles-register.component.html',
  styleUrls: ['./vehicles-register.component.css']
})
export class VehiclesRegisterComponent implements OnInit {

  vehicleReg = new FormGroup({
    vehicle_no:new FormControl('') ,
    model :new FormControl('') ,
    owner_name :new FormControl('') ,
    permit_range :new FormControl('') ,
    fuel_efficiency :new FormControl('') ,
    load_capacity :new FormControl('') ,
  })

  constructor(private service:DataServiceService) {
   }

  ngOnInit(): void {
  }

  register(){
    this.service.registerVehicle(this.vehicleReg.getRawValue()).subscribe(data=>console.log(data))

  }

}
