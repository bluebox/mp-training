import { ThisReceiver } from '@angular/compiler';
import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-previousvehicles',
  templateUrl: './previousvehicles.component.html',
  styleUrls: ['./previousvehicles.component.css']
})
export class PreviousvehiclesComponent implements OnInit {

  veh_no : any = window.sessionStorage.getItem('vehicle_no');
  vehicle = JSON.parse(this.veh_no);

  resp : any
  getOwner : any = window.sessionStorage.getItem('owner_id')
  owner = JSON.parse(this.getOwner)
    constructor(private service :GeneralService) {
   
     }

  ngOnInit(): void {
    this.getOwnerVehicle()

  }

  getOwnerVehicle(){
    this.service.getOwnerVehicle().subscribe(data => {(this.resp=data)
      window.sessionStorage.setItem('vehicle_no', JSON.stringify(data))} , (err) => alert('failed to load data'))
  }
  getVehicleNumber(){

  }
  deleteVehicle(id: any){
    this.service.deleteVehicle(id).subscribe(data => {confirm('This Vehicle will be deleted!! are you sure?'), this.ngOnInit()})
  }

  viewVehicleDetails(id: any){

  }


}
