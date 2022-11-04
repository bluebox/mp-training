import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-delivery-register',
  templateUrl: './delivery-register.component.html',
  styleUrls: ['./delivery-register.component.css']
})
export class DeliveryRegisterComponent implements OnInit {

  isAdmin:any;
  order_data:any;
  order_id:any;
  constructor(private aroute:ActivatedRoute, private service:DataServiceService,private cservice:CookieService) { 
    this.aroute.params.subscribe(data=>{this.order_id=data['order_id']})
    this.isAdmin = this.cservice.get('isAdmin');
    this.service.getOrder(this.order_id).subscribe(data=>{this.order_data = data;
    })}

  ngOnInit(): void {
  }

}
