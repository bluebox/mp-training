import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css']
})
export class CustomerViewComponent implements OnInit {
  customer_id:any
  customer_data:any
  constructor(private service:DataServiceService,private aroute:ActivatedRoute) { 
    this.aroute.params.subscribe(data=>{this.customer_id=data['customer_id']})
  }

  ngOnInit(): void {
    this.service.getCustomer(this.customer_id).subscribe(data=>{this.customer_data=data})
  }
  
}
