import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Food } from 'src/app/interfaces/food';
import { Menu } from 'src/app/interfaces/menu';
import { MenuItems } from 'src/app/interfaces/menuItems';
import { Restaurant } from 'src/app/interfaces/restaurant';
import { FoodService } from 'src/app/services/food.service';
import { MenuService } from 'src/app/services/menu.service';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-add-foodto-menu',
  templateUrl: './add-foodto-menu.component.html',
  styleUrls: ['./add-foodto-menu.component.css']
})
export class AddFoodtoMenuComponent implements OnInit {
  checked = false
  public menuList:Menu[]=[];
  public food:Food[]=[];
  public list:MenuItems[]=[];
  constructor(private fb:FormBuilder,private foodEle:FoodService,private res:RestaurantService,private menu:MenuService) { }

  ngOnInit(): void {
    this.getDataFood()
    this.getDataMenu()
   
  }
  foodForm=this.fb.group(
    {
    food_id:['', Validators.required],
    menu_id :['', Validators.required],
    }
  );


  getDataFood()
  {
    this.foodEle.getFood().subscribe(data=>{
      console.log(data);this.food=data
    ;console.log(this.food)})


  }

  getDataMenu()
  {
    this.menu.getMenu().subscribe(data=>{
      console.log(data);this.menuList=data
    ;console.log(this.menuList)})


  }


  onSubmit()
  {
    console.log(this.foodForm.value)
    
    if (this.foodForm.valid) {
      this.menu.postList(this.foodForm.value).subscribe((data)=>{
        console.log(data)
      })
      console.log('form submitted');
      console.log(this.foodForm.value)
    } else {
      console.log('notttt form submitted');
    }
    
    this.foodForm.reset()
  }
  }

