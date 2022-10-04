import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-vehicles-view',
  templateUrl: './vehicles-view.component.html',
  styleUrls: ['./vehicles-view.component.css']
})
export class VehiclesViewComponent implements OnInit {
  vehicle_no:any
  vehicle_data:any
  constructor(private aroute:ActivatedRoute,private service:DataServiceService) {
    this.aroute.params.subscribe(data=>this.vehicle_no=data['vehicle_no'])
    this.service.getVehicle(this.vehicle_no).subscribe(data=>this.vehicle_data=data)
   }

  ngOnInit(): void {
  }

}
