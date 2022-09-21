import { Component, OnInit,Input, Output, EventEmitter } from '@angular/core';
import { Product } from 'src/assets/products';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent  {


 @Input() product!: Product |undefined;
 @Output() notify = new EventEmitter();

}
