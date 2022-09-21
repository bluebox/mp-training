import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Product,products} from '../../assets/products';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-pro-detail',
  templateUrl: './pro-detail.component.html',
  styleUrls: ['./pro-detail.component.css']
})
export class ProDetailComponent implements OnInit {
  product:Product|undefined;

  constructor(private route:ActivatedRoute,
    private cartService:CartService) { }

    addToCart(product:Product){
      this.cartService.addToCart(product);
      window.alert("Your product has been added to your cart");

    }

  ngOnInit() {
    const routeParams=this.route.snapshot.paramMap;
    const productIdFromRoute = Number(routeParams.get('productId'));

    // Find the product that correspond with the id provided in route.
    this.product = products.find(product => product.id === productIdFromRoute);
  }

}
