import { Component, Input, OnInit } from '@angular/core';
import { DashboardComponent } from '../dashboard/dashboard.component';
@Component({
  selector: 'app-cust-cart',
  templateUrl: './cust-cart.component.html',
  styleUrls: ['./cust-cart.component.css']
})
export class CustCartComponent implements OnInit {
  @Input() cartId!:number 
  constructor() { }

  ngOnInit(): void {
  }

}
