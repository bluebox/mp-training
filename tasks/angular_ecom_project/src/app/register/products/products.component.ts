import { Component, OnInit } from '@angular/core';
import { DataserveService } from 'src/app/dataserve.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private dashdata: DataserveService) {
    this.dashdata.getProducts().subscribe(newdata => {
      // delete newdata.image;
      console.log("ygvhb")
      this.data = newdata
      console.log(newdata)
    }
    )
  }
  data: any;
  ngOnInit(): void {


  }
  quantity: number=0;
  name: string = 'bhargavi';
  cart:string="Add to Cart";
  cartId:number[]=[]
  Cart(id:number){
    // this.quantity=this.quantity + 1
    // this.cart="Added"
    console.log("cart",id, this.quantity)
    this.cartId.push(id)
  
  }

   remove(){
    if (this.quantity > 0){
    this.quantity=this.quantity -1
    this.cart=String(this.quantity)
   }
   if (this.quantity == 0){
    this.cart="Add to Cart"
   }}

   add(){
    this.quantity=this.quantity + 1
    this.cart=String(this.quantity)
   }
}
