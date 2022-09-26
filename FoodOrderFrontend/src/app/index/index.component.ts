import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Food } from '../interfaces/food';
import { FoodService } from '../services/food.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  

 

  public food:Food[]=[];
  
  constructor(private element:FoodService) { 
    
  }

  ngOnInit(): void {
    this.getData()
   
  }

  getData()
  {
    this.element.getFood().subscribe(data=>{
      console.log(data);this.food=data
    ;console.log(this.food)})


  }

  foodClicked=(f:Food)=>
  {
    this.element.getOneFood(f.food_id).subscribe(data=>{
      console.log(data)})
  }

}
