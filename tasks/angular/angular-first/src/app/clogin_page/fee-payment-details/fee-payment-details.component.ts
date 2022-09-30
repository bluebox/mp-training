import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-fee-payment-details',
  templateUrl: './fee-payment-details.component.html',
  styleUrls: ['./fee-payment-details.component.css']
})
export class FeePaymentDetailsComponent implements OnInit {

  constructor(private service: ServiceService) { }
  paymentDetails!: any;
  data: any= sessionStorage.getItem('contractDetails');
  contractDetails: any = JSON.parse(this.data);
  arr : any = [];
  ngOnInit(): void {
    for (let i of this.contractDetails) {
      this.service.getFeePaymentDetails(i.contract_id).subscribe(
        (data: any) => { this.arr.push(data); }
      )
      
    }
    console.log(this.arr);
    
  }




}
