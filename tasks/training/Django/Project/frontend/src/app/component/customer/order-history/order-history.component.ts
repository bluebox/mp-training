
import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  response: any=[]
  customer = window.sessionStorage.getItem('customer_id');
  constructor(private service : GeneralService) { }

  ngOnInit(): void {
    this.getOrderHistory()
  }

  getOrderHistory(){
    this.service.orderHistory().subscribe(data => {(this.response=data),
      console.log(this.response)
      for(let i=0; i<this.response.length; i++){
        this.response[i]['booking_date'] =this.response[i]['booking_date'].slice(0,10)

        console.log(this.response[i]['booking_date'])
  }
    } );
  }
}
