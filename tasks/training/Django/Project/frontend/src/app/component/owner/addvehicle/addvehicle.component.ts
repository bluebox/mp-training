import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { compileNgModule, ThisReceiver } from '@angular/compiler';
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
  imageUrl: any
  constructor(private service: GeneralService,private http: HttpClient, private route : Router) { }
  data_1 : any = window.sessionStorage.getItem('owner_id')
  data = JSON.parse(this.data_1);
  veh:any;
  submit !: boolean

  ngOnInit(): void {
    this.vehicle = new FormGroup({
      vehicle_no: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required),
      brand: new FormControl('', Validators.required ),
      model: new FormControl('', Validators.required),
      image: new FormControl('', Validators.required),
      price_day: new FormControl('', Validators.required),
    })
  }
  addVehicle()
  {
    let data = this.vehicle.value
    data.image = this.imageUrl
    this.veh=this.vehicle.value
    console.log(this.veh)

    this.service.addVehicle(this.veh).subscribe(data=> {(console.log(data)), alert("Added successfully"), window.location.reload()}, (err)=> alert("Enter valid details"))

  }
get formData(){
  return this.vehicle.controls
}
  getImageUrl(e:any) {
    console.log(e)
    let formdata = new FormData()
    formdata.append('img', e.target.files[0])
    this.http.post('http://127.0.0.1:8000/save-file/', formdata).subscribe(data => {
      let res = JSON.stringify(data)
      console.log(JSON.parse(res)['url'])
      this.imageUrl = JSON.parse(res)['url']
    })
  }

}
