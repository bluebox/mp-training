import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Food } from 'src/app/interfaces/food';
import { FoodService } from 'src/app/services/food.service';
import { AddFoodComponent } from '../../add-food/add-food.component';

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.css']
})
export class FoodComponent implements OnInit {

  public food:Food[]=[];
  constructor(public dialog:MatDialog, private foodEle:FoodService) { }

  ngOnInit(): void {
    this.getDataFood()
  }
  onClick()
  {
    this.dialog.open(AddFoodComponent, {
      autoFocus: false,
      maxHeight: '90vh' //you can adjust the value as per your view
})
  }

  getDataFood()
  {
    this.foodEle.getFood().subscribe(data=>{
      console.log(data);this.food=data
    ;console.log(this.food)})


  }
}
