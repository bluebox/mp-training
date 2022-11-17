import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  transactions : any;
  constructor(private http:HttpserviceService) { }
  displayedColumns :string[]=['trans_id','trans_date','trans_type','Total_amount','trans_status','Appointment_id']

  ngOnInit(): void {
    this.subscription = this.http.getTransactions().subscribe(data => {this.transactions = data ;console.log(data)})
  }

}
