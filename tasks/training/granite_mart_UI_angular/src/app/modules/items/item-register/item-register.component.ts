import { ConditionalExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-item-register',
  templateUrl: './item-register.component.html',
  styleUrls: ['./item-register.component.css']
})
export class ItemRegisterComponent implements OnInit {
  stores:any
  items:any
  ItemReg = new FormGroup({
    contains_id : new FormControl('',Validators.required),
    store_id : new FormControl('',Validators.required),
    item_id  : new FormControl('',Validators.required),
    price    : new FormControl('',Validators.required),
  })
  constructor(private service:DataServiceService,private router:Router) {
    this.service.getStores().subscribe(data=>{this.stores=data;console.log(data)})
    this.service.getRawItems().subscribe(data=>{this.items=data;console.log(data)})
   }

  ngOnInit(): void {
  }
  register(){
    console.log(this.ItemReg.getRawValue())
      this.service.registerItem(this.ItemReg.getRawValue()).subscribe(data=>console.log(data))
      this.router.navigate(['itemsList'])
  }
}
