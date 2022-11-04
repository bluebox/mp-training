import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit {
  orderList:any;
  isAdmin:any;
  order_id:any;
  loginValue:any=localStorage.getItem('login')
  constructor(private service:DataServiceService,private cservice:CookieService, private router:Router, private aroute:ActivatedRoute) {
    this.isAdmin=this.cservice.get('isAdmin')
   }

  ngOnInit(): void {
    this.service.getOrders().subscribe(data=>{this.orderList=data;console.log(data)})
  }

  view(order_id:any){
    this.router.navigate(['viewOrder',order_id])
  }
}
