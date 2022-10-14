import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Food } from '../interfaces/food';
import { Restaurant } from '../interfaces/restaurant';
import { Search } from '../interfaces/searchResult';
import { FoodService } from '../services/food.service';
import { LoginService } from '../services/login.service';
import { RestaurantService } from '../services/restaurant.service';
import { SearchService } from '../services/search.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  

 
  public search:string="";
  public food:Food[]=[];

  public restaurant:Restaurant[]=[];
  public searchResult:Search[]=[];
  
  constructor(private foodEle:FoodService,private res:RestaurantService, private fsearch:SearchService, private router:Router,private loginService:LoginService) { 
    
  }

  ngOnInit(): void {
    this.loginCheck()
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
    this.foodEle.getOneFood(f.food_name).subscribe(data=>{
      console.log(data)})
      this.fsearch.getSearch(f.food_id).subscribe(data=>{
        console.log(data);this.restaurant=data})
  
      this.router.navigate(['/customer/searchresult',f.food_name])
  
  }

  resClicked(r:Restaurant){}


  searchFood=(item:string)=>
  {
    this.fsearch.getSearch(item).subscribe(data=>{
      console.log(data);this.restaurant=data})

    this.router.navigate(['/customer/searchresult',item])

    
  }
  loginCheck(){
    this.loginService.loginCheck().subscribe((data)=>{
      console.log(data)

     if(data.body.email){

     }
    //  else{
    //   this.router.navigate(['/customer/login'])
    //  }
    
    })
    
  }
}

