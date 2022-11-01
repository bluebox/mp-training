import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { GeneralService } from 'src/app/general.service';
@Component({
  selector: 'app-available-vehicle',
  templateUrl: './available-vehicle.component.html',
  styleUrls: ['./available-vehicle.component.css']
})
export class AvailableVehicleComponent implements OnInit {
text : string=''

  sort(e: any) {
    console.log(e);
    this.response = this.totalObjects.filter((vehicle: any ) => {
      console.log(vehicle.type);

      if(e.target.value != ''){
        if(vehicle.type == e.target.value)
          return vehicle

      }else{
        return vehicle

      }
  })
  console.log(this.response);

}

  vehicle_obj : Subscription=Subscription.EMPTY
  response: any
  totalObjects: any
  resp: any


  constructor(private service:GeneralService, http : HttpClient, private route: Router) { }

  ngOnInit(): void {

    this.show_vehicles()

  }


  show_vehicles(){
    this.vehicle_obj=this.service.getVehicle().subscribe(data=>{(this.totalObjects=data);
    // console.log(data)
    this.response=data
  }
)
  }
  bookVehicle( id:any ){

    console.log(id)
    this.route.navigate(['book-vehicle', id])


  }
  searchVehicle(){
    this.service.searchVehicle(this.text).subscribe(data=>{(this.response=data),
    console.log(this.response)})
  }

  checkDate(from: any, to:any){
    console.log(from + " "+ to)
  }
}
