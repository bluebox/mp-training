import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { GeneralService } from 'src/app/general.service';
@Component({
  selector: 'app-available-vehicle',
  templateUrl: './available-vehicle.component.html',
  styleUrls: ['./available-vehicle.component.css']
})
export class AvailableVehicleComponent implements OnInit {

  // vehicle_list:any=[]
  vehicle_obj : Subscription=Subscription.EMPTY
  response: any
  resp: any

  constructor(private service:GeneralService, http : HttpClient, private route: Router) { }

  ngOnInit(): void {
    this.show_vehicles()
  }

  show_vehicles(){
    this.vehicle_obj=this.service.getVehicle().subscribe(data=>{(this.response=data);
    console.log(data)}
)
  }
  bookVehicle(vehicle : any){
   console.log(vehicle);
   this.resp = window.sessionStorage.setItem('vehicle',JSON.stringify(vehicle));
   this.route.navigate(['book-vehicle'])
  }

  // show_vehicles(){
  //   this.vehicle_obj=this.service.get_vehicle().subscribe(data=>{(window.sessionStorage.setItem('vehicle_no',JSON.stringify(data)));
  //   console.log(data)}
  //   )
  // }
}
