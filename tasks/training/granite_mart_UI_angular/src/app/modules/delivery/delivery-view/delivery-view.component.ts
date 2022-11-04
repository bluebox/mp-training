import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-delivery-view',
  templateUrl: './delivery-view.component.html',
  styleUrls: ['./delivery-view.component.css']
})
export class DeliveryViewComponent implements OnInit {

  isAdmin:any;
  order_data:any;
  order_id:any;
  constructor(private router:Router,private aroute:ActivatedRoute, private service:DataServiceService,private cservice:CookieService) { 
    this.aroute.params.subscribe(data=>{this.order_id=data['order_id']})
    this.isAdmin = this.cservice.get('isAdmin');
    this.service.getDelivery(this.order_id).subscribe(data=>{this.order_data = data;
      console.log(data)
      })
  }

  ngOnInit(): void {
  }

  gohome(){
    this.router.navigate(['itemsList'])
  }

}
