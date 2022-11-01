import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-createfacility',
  templateUrl: './createfacility.component.html',
  styleUrls: ['./createfacility.component.css']
})
export class CreatefacilityComponent implements OnInit {
facilityform : any 
msg : string =''
  constructor( private service : ServicesService) {
    this.facilityform= new FormGroup({
      facility_id:new FormControl('',Validators.required),
      facility_dept: new FormControl('',Validators.required),
      facility_location: new FormControl(),

    })
   }

  ngOnInit(): void {
  } 
createfacility(){
  console.log(this.facilityform.value);
  this.msg="please enter all the details"
  if(this.facilityform.Valid){

  
  this.service.postfacility(this.facilityform.value).subscribe(data => console.log(data)
  )
}
  
}
clear(){
  this.msg=''
}
}
