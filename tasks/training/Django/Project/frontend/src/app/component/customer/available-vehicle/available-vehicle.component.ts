import { Component, OnInit } from '@angular/core';
import { GeneralService } from 'src/app/general.service';
@Component({
  selector: 'app-available-vehicle',
  templateUrl: './available-vehicle.component.html',
  styleUrls: ['./available-vehicle.component.css']
})
export class AvailableVehicleComponent implements OnInit {

  vehicle_list:any=[]
  constructor(private service:GeneralService) { }

  ngOnInit(): void {
    this.show_vehicles()
  }

  show_vehicles(){
    this.service.get_vehicle().subscribe(data=>{
      this.vehicle_list=data
    })
  }
}
