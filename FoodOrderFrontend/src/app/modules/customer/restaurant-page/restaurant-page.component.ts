import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Food } from 'src/app/interfaces/food';
import { Restaurant } from 'src/app/interfaces/restaurant';
import { FoodService } from 'src/app/services/food.service';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-restaurant-page',
  templateUrl: './restaurant-page.component.html',
  styleUrls: ['./restaurant-page.component.css']
})
export class RestaurantPageComponent implements OnInit {


  name:any;
  res:any;
  obj:any;
  foodL:Food[]=[];
  id:any;
  constructor(private route:ActivatedRoute,private router:Router, private resSer:RestaurantService, private foodSer:FoodService) { }

  ngOnInit(): void {
    let item=this.route.snapshot.paramMap.get('name')
    this.name=item
    console.log(this.name)
    this.getData()
    this.getFoods()
  }


  getData()
  {
    this.resSer.getOneRes(this.name).subscribe(data=>{
      this.res=data
      console.log("_____________")
     
     })
     



  }

  getFoods()
  {
    this.foodSer.getFoodRes(this.name).subscribe(data=>{
      this.foodL=data
      console.log("Hii")
      console.log(this.foodL)})

  }

}
