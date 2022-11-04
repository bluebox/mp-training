import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css']
})
export class CustomerViewComponent implements OnInit {
  customer_id:any
  customer_data:any=""
  isAdmin:any
  constructor(private service:DataServiceService,private router:Router,private aroute:ActivatedRoute,private cservice:CookieService) { 
    this.isAdmin=this.cservice.get('isAdmin')
    this.aroute.params.subscribe(data=>{this.customer_id=data['customer_id']})
  }

  ngOnInit(): void {
    this.service.getCustomer(this.customer_id).subscribe(data=>{this.customer_data=data})
  }

  update(username:any){
    this.router.navigate(['customerEdit',username.toString()])
  }
  
}
