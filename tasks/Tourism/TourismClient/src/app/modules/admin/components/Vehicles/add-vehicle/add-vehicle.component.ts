import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent implements OnInit {

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.dataservice.getVehicle(parseInt(res['id'])).subscribe(data=> {
          let vehicleString = JSON.stringify(data)
          let vehicleObj = JSON.parse(vehicleString)
          this.VehicleForm.get('vehicle_type')?.setValue(vehicleObj.vehicle_type);
          this.VehicleForm.get('model')?.setValue(vehicleObj.model);
          this.VehicleForm.get('vehicle_number')?.setValue(vehicleObj.vehicle_number);
          this.VehicleForm.get('isAC')?.setValue(vehicleObj.isAC);
          this.VehicleForm.get('total_seats')?.setValue(vehicleObj.total_seats);
        });
      }
    })
  }
  id!: number

  VehicleForm: FormGroup = new FormGroup({
    vehicle_type : new FormControl('', [Validators.required]),
    model : new FormControl('', [Validators.required, Validators.email]),
    vehicle_number : new FormControl('', [Validators.required]),
    isAC : new FormControl('', [Validators.required]),
    total_seats : new FormControl('', [Validators.required])
  })


  ngOnInit(): void {
  }

  addVehicleObj() {
    if(this.id){
      this.dataservice.editVehicle(this.VehicleForm.value, this.id).subscribe(data=>{
        console.log(data)
        this.router.navigate(['admin/vehicles/vehicleList'])
      })
    }else{
      this.dataservice.addVehicle(this.VehicleForm.value).subscribe(data=>{
        console.log(data)
        this.router.navigate(['admin/vehicles/vehicleList'])
      })
    }
  }

}
