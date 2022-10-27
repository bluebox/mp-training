import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-fee-payment-details',
  templateUrl: './fee-payment-details.component.html',
  styleUrls: ['./fee-payment-details.component.css']
})
export class FeePaymentDetailsComponent implements OnInit {
page= 1;
totalPages: any;

  constructor(private service: ServiceService) { }
  paymentDetails!: any;
  data: any= sessionStorage.getItem('contractDetails');
  contractDetails: any = JSON.parse(this.data);
  arr : any = [];
  ngOnInit(): void {
    for (let i of this.contractDetails) {
      this.service.getFeePaymentDetails(i.contract_id).subscribe(
        (data: any) => { this.arr.push(data);
        }
      )
    }
    
  }
  getTotal() {
    let total = 0;
  
    this.arr.forEach((item: any) => {
      total += Number(item.total_payment);
    });
  
    return total.toFixed(2);
  }

}
