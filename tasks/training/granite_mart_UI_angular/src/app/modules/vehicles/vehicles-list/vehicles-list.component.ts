import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-vehicles-list',
  templateUrl: './vehicles-list.component.html',
  styleUrls: ['./vehicles-list.component.css']
})
export class VehiclesListComponent implements OnInit {
  vehicles_list:any
  
  constructor(private service:DataServiceService,private router:Router) {

    this.service.getVehicles().subscribe(data=>this.vehicles_list=data)
   }

  ngOnInit(): void {

  }
  addVehicle(){
    this.router.navigate(['vehicleRegistration'])
  }
  updateVehicle(vehicle_no:any){
    this.router.navigate(['vehicleEdit', vehicle_no.toString()])
  }
  deleteVehicle(vehicle_no:any){
    this.service.deleteVehicle(vehicle_no).subscribe(data=>console.log(data))
  }

}
