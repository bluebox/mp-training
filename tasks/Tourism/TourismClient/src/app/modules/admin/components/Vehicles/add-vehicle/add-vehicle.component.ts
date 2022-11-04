import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent implements OnInit {

  routeSubscription!: Subscription;
  getVehicleSubscription!: Subscription;
  editVehicleSubscription!: Subscription;
  addVehicleSubscription!: Subscription;

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getVehicleSubscription = this.dataservice.getVehicle(parseInt(res['id'])).subscribe(
          data=> {
            let vehicleString = JSON.stringify(data)
            let vehicleObj = JSON.parse(vehicleString)
            this.VehicleForm.get('vehicle_type')?.setValue(vehicleObj.vehicle_type);
            this.VehicleForm.get('model')?.setValue(vehicleObj.model);
            this.VehicleForm.get('vehicle_number')?.setValue(vehicleObj.vehicle_number);
            this.VehicleForm.get('isAC')?.setValue(vehicleObj.isAC);
            this.VehicleForm.get('total_seats')?.setValue(vehicleObj.total_seats);
          },
          err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
        );
      }
    })
  }
  id!: number

  VehicleForm: FormGroup = new FormGroup({
    vehicle_type : new FormControl('', [Validators.required]),
    model : new FormControl('', [Validators.required]),
    vehicle_number : new FormControl('', [Validators.required]),
    isAC : new FormControl('', [Validators.required]),
    total_seats : new FormControl('')
  })

  get formObj(){
    return this.VehicleForm.controls
  }

  ngOnInit(): void {
  }

  addVehicleObj() {
    if(this.VehicleForm.valid){
      if(this.id){
        this.editVehicleSubscription = this.dataservice.editVehicle(this.VehicleForm.value, this.id).subscribe(
          (data: any)=>{
          alert(`Vehicle number ${data.vehicle_number} is Updated successfully`)
          this.router.navigate(['admin/vehicles/vehicleList'])
        },
        err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
        )
      }else{
        this.addVehicleSubscription = this.dataservice.addVehicle(this.VehicleForm.value).subscribe(
          (data: any)=>{
          alert(`Vehicle number ${data.vehicle_number} is added successfully`)
          this.router.navigate(['admin/vehicles/vehicleList'])
        },
        err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
        )
      }
    }
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getVehicleSubscription){
      this.getVehicleSubscription.unsubscribe()
    }
    if(this.editVehicleSubscription){
      this.editVehicleSubscription.unsubscribe()
    }
    if(this.addVehicleSubscription){
      this.addVehicleSubscription.unsubscribe()
    }
  }

}
