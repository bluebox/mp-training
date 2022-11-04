import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { timestamp } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-orders-register',
  templateUrl: './orders-register.component.html',
  styleUrls: ['./orders-register.component.css']
})
export class OrdersRegisterComponent implements OnInit {
  tot_amount:number=0
  item_count:number=0
  username:any
  cart_data:any
  orderForm = new FormGroup({
    deliveryAddress :new FormControl('',[Validators.required])

  })
  
  contains_id_list:Array<any>=[]

  constructor(private service:DataServiceService,private aroute:ActivatedRoute,private router:Router) { 
    this.aroute.params.subscribe(data=>this.username=data['username'])
    this.service.getCart(this.username).subscribe(data=>{
      this.cart_data=data;console.log(this.cart_data);
      console.log(this.cart_data.data[0])
      for(let i of this.cart_data.data){
        this.tot_amount+=parseInt(i.price) * parseInt(i.quantity);
        this.contains_id_list.push(i.contains_id)
        this.item_count+=parseInt(i.quantity)
      }
      console.log(this.contains_id_list)
    })
    

  }

  get Address(){
    return this.orderForm.get("deliveryAddress")
  }

  ngOnInit(): void {
  }

  buy(){
    
    if(this.orderForm.valid){
      let currentTime = new Date();
      console.log(currentTime.toString());
      let pay_load={'order_time':currentTime,'deliveryAddress':this.orderForm.getRawValue()['deliveryAddress'],'contains_ids':this.contains_id_list}
      this.service.createOrder(pay_load).subscribe(data=>{alert(data)},err=>{alert(err)})
      this.service.deleteCart(-1).subscribe(data=>{console.log(data)})
      this.router.navigate(['orderList'])
    } 
  }

}
