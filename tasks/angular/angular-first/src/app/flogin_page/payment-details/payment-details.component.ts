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
  constructor(private service : ServiceService) { }
  
  ngOnInit(): void {
    this.service.getPaymentDetails(this.data_parse).subscribe((data: any) => { this.recevied_data  = data; });
  }

}
