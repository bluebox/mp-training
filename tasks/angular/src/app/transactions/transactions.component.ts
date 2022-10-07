import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../services/userservice.service';
import {Router} from "@angular/router"

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent  {
transactionData:any;
data:any;
  constructor(private userdata:UserserviceService, private router:Router) { 
    if (localStorage.getItem("customer_refresh_token")) {
      //  console.log(localStorage.getItem("customer_refresh_token"))
        
        this.router.navigate(['/cusdashBoard/transactionlist'])
        // this.userdata.setmsg(this.signInCheck)
        // this.customerData = localStorage.getItem("customer_data")
        // this.parsedData = JSON.parse(this.customerData)
        // this.accountData = localStorage.getItem("account_data")
        // this.parsedAccountData = JSON.parse(this.accountData)
        // console.log(this.parsedAccountData);
        // this.signInCheck = false
        // this.userdata.setmsg(this.signInCheck)
        this.data = this.userdata.getTransactionData()
          console.log(this.data);
          this.transactionData = this.data
        
  
      }
      else{
        this.router.navigate(['/home'])
  
  }
}

  ngOnInit(){
    
  }
//  data = [{'transaction_id': 44, 'transaction_amount': '500', 'sender_id': 9, 'receiver_id': 10, 'sender_status': 'Debited', 'receiver_status': 'Credited'}, {'transaction_id': 45, 'transaction_amount': '36000', 'sender_id': 10, 'receiver_id': 9, 'sender_status': 'Debited', 'receiver_status': 'Credited'}]
}
