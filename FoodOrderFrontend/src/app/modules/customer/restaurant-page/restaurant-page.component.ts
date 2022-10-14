import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cart } from 'src/app/interfaces/cart';
import { Food } from 'src/app/interfaces/food';
import { Restaurant } from 'src/app/interfaces/restaurant';
import { CartService } from 'src/app/services/cart.service';
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
  quantity:any=1;
  cost:Number=0;
  cartItem:Cart[]=[];
  getCartList:Cart[]=[];
  x=19;
  constructor(private route:ActivatedRoute,private router:Router, private resSer:RestaurantService, private foodSer:FoodService, private cart:CartService) { }

  ngOnInit(): void {
  
    this.getCart()
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

  incQuan(id:any){
    this.quantity=this.quantity+1
  }

  decQuan(id:any){

      if(this.quantity<2)
      {
        this.quantity=1
      }
      else{
   
      this.quantity=this.quantity-1
    
      }
    
  }




  postCart(id:any,price:any)
  { 
    
    this.ngOnInit()
    this.getCart()
    console.log("hii1")  
    console.log(id)
    console.log(price)
    console.log(this.quantity)
    console.log("hii2")
    price=price*this.quantity
    this.x=this.x+1
    // var items=new Array("of001",Number(this.quantity),Number(price),"c001",this.res.restaurant_id,id,"m001")
    let items:Cart={
      order_food_id:"of00"+String(this.x),
      customer_id:"c001",
      restaurant_id :this.res.restaurant_id,
      food_id :id,
      quantity:Number(this.quantity),
      price:Number(price),
      menu_id:"m001"
    }
    this.cost=this.cost+price
    console.log(items)
    this.cart.postCart(items).subscribe((data)=>{
      console.log(data)
    })
    this.quantity=1
   this.ngOnInit()

    this.getCart()
  }

  getCart()
  {
    this.cart.getCart().subscribe(data=>{
      this.getCartList=data
     console.log(typeof(this.res))
     })
     


  }

  deleteitem(id:any,quan:any,price:any)
  {
    this.ngOnInit()
    console.log("d")
    this.cart.delItem(id).subscribe(()=>{
      
     console.log("data deleted")
     })

     
     this.ngOnInit()

    this.getCart()
    this.cost=Number(this.cost)-Number(quan*price)
  }

}
