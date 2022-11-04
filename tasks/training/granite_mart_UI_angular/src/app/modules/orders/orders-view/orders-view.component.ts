import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-orders-view',
  templateUrl: './orders-view.component.html',
  styleUrls: ['./orders-view.component.css']
})
export class OrdersViewComponent implements OnInit {
  isAdmin:any;
  order_data:any;
  order_id:any;
  constructor(private router:Router,private aroute:ActivatedRoute, private service:DataServiceService, private cservice:CookieService) {
    this.isAdmin=this.cservice.get('isAdmin')
    this.aroute.params.subscribe(data=>this.order_id=data['order_id'])
    this.service.getOrder(this.order_id).subscribe(data=>{this.order_data=data;console.log(this.order_data[0])})
   }

  ngOnInit(): void {
  }
  
  delivery(){
    this.router.navigate(['viewDelivery',this.order_id.toString()])
  }

}
