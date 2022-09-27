import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Food } from '../interfaces/food';
import { Restaurant } from '../interfaces/restaurant';
import { FoodService } from '../services/food.service';
import { RestaurantService } from '../services/restaurant.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  

 

  public food:Food[]=[];
  public restaurant:Restaurant[]=[];
  
  constructor(private foodEle:FoodService,private res:RestaurantService) { 
    
  }

  ngOnInit(): void {
    this.getDataFood()
    this.getDataRes()
   
  }

  getDataFood()
  {
    this.foodEle.getFood().subscribe(data=>{
      console.log(data);this.food=data
    ;console.log(this.food)})


  }

  getDataRes()
  {
    this.res.getRes().subscribe(data=>{
      console.log(data);this.restaurant=data
    ;console.log(this.restaurant)})


  }

  foodClicked=(f:Food)=>
  {
    this.foodEle.getOneFood(f.food_id).subscribe(data=>{
      console.log(data)})
  }

  resClicked(r:Restaurant){}

}
