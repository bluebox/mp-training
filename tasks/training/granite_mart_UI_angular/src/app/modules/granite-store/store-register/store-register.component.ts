import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-store-register',
  templateUrl: './store-register.component.html',
  styleUrls: ['./store-register.component.css']
})
export class StoreRegisterComponent implements OnInit {

  storeReg=new FormGroup({
    store_id :new FormControl(''),
    store_name :new FormControl(''),
    established_year :new FormControl(''),
    store_description :new FormControl(''),
    contact :new FormControl(''),
    website :new FormControl(''),
    address :new FormControl(''),
  })
  constructor(private service:DataServiceService) { }

  ngOnInit(): void {
  }

  register(){
    this.service.storeRegistration(this.storeReg.getRawValue()).subscribe(data=>console.log(data))
  }

}
