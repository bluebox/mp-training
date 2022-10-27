import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrls: ['./payment-details.component.css']
})
export class PaymentDetailsComponent implements OnInit {
  recevied_data: any;
  data: any=window.localStorage.getItem('fuser');
  data_parse = JSON.parse(this.data).id;
  totalPages: any;
  constructor(private service : ServiceService) { }
  page = 1;
  ngOnInit(): void {
    this.getPaymentDetails(0)
    // this.service.getPaymentDetails(this.data_parse).subscribe((data: any) => { this.recevied_data  = data; });
  }

  getPaymentDetails(page : number){
    this.service.getPaymentDetails(page,this.data_parse).subscribe((data: any ) => { this.recevied_data = data.pageItems;
    this.page = data.page ;
  this.totalPages = data.totalPages});
  }

  getTotal() {
    let total = 0;
    this.recevied_data?.forEach((item: any) => {
      total += Number(item.earned);
    });
  
    return total.toFixed(2);
  }

}
