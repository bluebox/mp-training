import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { GeneralService } from 'src/app/general.service';

@Component({
  selector: 'app-addvehicle',
  templateUrl: './addvehicle.component.html',
  styleUrls: ['./addvehicle.component.css']
})
export class AddvehicleComponent implements OnInit {

  vehicle!: FormGroup
  constructor(private service: GeneralService) { }

  ngOnInit(): void {
    this.vehicle = new FormGroup({
      vehicle_no: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required),
      brand: new FormControl('', Validators.required ),
      model: new FormControl('', Validators.required),
      image: new FormControl('', Validators.required),
      owner_id: new FormControl('', Validators.required),
      price_km: new FormControl('', Validators.required),
      price_hour: new FormControl('', Validators.required),
      price_day: new FormControl('', Validators.required),
    })
  }
  add_vehicle()
  {
    this.service.add_vehicle(this.vehicle.value).subscribe(data=> console.log(data))
  }

}
