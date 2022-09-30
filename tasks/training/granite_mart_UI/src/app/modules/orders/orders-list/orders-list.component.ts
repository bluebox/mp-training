import { Component, OnInit } from '@angular/core';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit {
  orderList:any
  constructor(private service:DataServiceService) { }

  ngOnInit(): void {
    this.service.getOrders().subscribe(data=>{this.orderList=data;console.log(data)})
    
  }

}
