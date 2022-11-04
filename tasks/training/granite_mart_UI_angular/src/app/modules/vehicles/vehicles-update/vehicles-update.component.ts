import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-vehicles-update',
  templateUrl: './vehicles-update.component.html',
  styleUrls: ['./vehicles-update.component.css']
})
export class VehiclesUpdateComponent implements OnInit {

  vehicleUpdate = new FormGroup({
    vehicle_no: new FormControl(''),
    model: new FormControl(''),
    owner_name: new FormControl(''),
    permit_range: new FormControl(''),
    fuel_efficiency: new FormControl(''),
    load_capacity: new FormControl(''),
  })
  vehicle_no: any;
  vehicle_data: any;
  constructor(private service: DataServiceService, private aroute: ActivatedRoute) {
    this.aroute.params.subscribe(data => this.vehicle_no = data['vehicle_no'])
    this.service.getVehicle(this.vehicle_no).subscribe(data =>{this.vehicle_data = data;
      this.vehicleUpdate.setValue(
        {
          "vehicle_no": this.vehicle_data.vehicle_no,
          "model": this.vehicle_data.model,
          "owner_name": this.vehicle_data.owner_name,
          "permit_range": this.vehicle_data.permit_range,
          "fuel_efficiency": this.vehicle_data.fuel_efficiency,
          "load_capacity": this.vehicle_data.load_capacity,
        }
      )})
   
  }

  ngOnInit(): void {
  }

  update() {
    this.service.updateVehicle(this.vehicleUpdate.getRawValue(), this.vehicle_no).subscribe(data => console.log(data))
  }

}
