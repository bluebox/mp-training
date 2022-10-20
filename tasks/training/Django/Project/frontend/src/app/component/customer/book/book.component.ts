import { GeneralService } from 'src/app/general.service';
import { compileNgModule } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  response : any =  window.sessionStorage.getItem('owner')
  responseData = JSON.parse(this.response)


  vehicle! : FormGroup
  vehicle_data : any = window.sessionStorage.getItem('vehicle');
  vehicle_obj = JSON.parse(this.vehicle_data)

  customer_obj : any = window.sessionStorage.getItem('customer_id');
  customer = JSON.parse(this.customer_obj);
  pickupDate : any
  returnDate : any
  newReturnDate : any
  newPickDate : any
  msg : any
  constructor(private service : GeneralService, private router : Router) {



  }
  ngOnInit(): void {

      if(this.vehicle_obj){
        this.vehicle = new FormGroup({
        pickup_date: new FormControl('', Validators.required),
        return_date: new FormControl('', Validators.required)

        })
      }
  }
  
  dateCheck(){
    let date = new Date();
    this.pickupDate = this.vehicle.value['pickup_date'];
    this.returnDate= this.vehicle.value['return_date'];

    this.newPickDate = new Date(this.pickupDate);
    this.newReturnDate = new Date(this.returnDate);
    console.log("pickup=" + this.newPickDate + "return" + this.newReturnDate + "today="+ date)

    if(this.newPickDate > date && this.newReturnDate >= this.newPickDate){
      this.bookSelectedVehicle()
    }
    else{
      alert('Choose Valid date')
    }
  }

  dateConverter(pDate: any) {
    var date = new Date(pDate);
      let month = ("0" + (date.getMonth() + 1)).slice(-2);
      let p_day = ("0" + date.getDate()).slice(-2);
    return [date.getFullYear(), month, p_day].join("-");
  }

  bookSelectedVehicle(){

    let veh = {
      "vehicle_no": this.vehicle_obj.vehicle_no,
      "owner_id": this.vehicle_obj.owner_id,
      'pickup_date': this.dateConverter(this.newPickDate),
      'return_date': this.dateConverter(this.returnDate)
    }

    let post=this.service.bookVehicle(veh).subscribe((data : any) =>{(this.msg=data)
      window.sessionStorage.setItem('customer_id',JSON.stringify(this.msg)),  console.log(data)}
      , (err) => {alert(' Select date')},)


  }
}

