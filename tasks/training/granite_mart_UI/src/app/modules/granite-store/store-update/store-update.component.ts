import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-store-update',
  templateUrl: './store-update.component.html',
  styleUrls: ['./store-update.component.css']
})
export class StoreUpdateComponent implements OnInit {

  store_id:any
  store_data:any

  storeUpdate=new FormGroup({
    store_id :new FormControl(''),
    store_name :new FormControl(''),
    established_year :new FormControl(''),
    store_description :new FormControl(''),
    contact :new FormControl(''),
    website :new FormControl(''),
    address :new FormControl(''),
  })
  constructor(private aroute:ActivatedRoute, private service:DataServiceService) {
    this.aroute.params.subscribe(data=>this.store_id=data['store_id'])
    this.service.getStore(this.store_id).subscribe(data=>this.store_data=data)
    this.storeUpdate.setValue({
      store_id :this.store_data.store_id,
      store_name :this.store_data.store_name,
      established_year :this.store_data.established_year,
      store_description :this.store_data.store_description,
      contact :this.store_data.contact,
      website :this.store_data.website,
      address :this.store_data.address,
   })}

  ngOnInit(): void {
  }

  update(){
    this.service.updateStore(this.store_id,this.storeUpdate.getRawValue()).subscribe(data=>console.log(data))
  }

}
