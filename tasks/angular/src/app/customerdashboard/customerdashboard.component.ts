import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router"
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-customerdashboard',
  templateUrl: './customerdashboard.component.html',
  styleUrls: ['./customerdashboard.component.css']
})
export class CustomerdashboardComponent{
signInCheck:any = true
customerData:any;
parsedData:any;
accountData:any;
parsedAccountData:any;
trasactionData:any;
  constructor( private router:Router, private userdata:UserserviceService) {
     
    if (localStorage.getItem("customer_refresh_token")) {
    //  console.log(localStorage.getItem("customer_refresh_token"))
      
      this.router.navigate(['/cusdashBoard'])
      this.userdata.setmsg(this.signInCheck)
      // this.customerData = localStorage.getItem("customer_data")
      // this.parsedData = JSON.parse(this.customerData)
      // this.accountData = localStorage.getItem("account_data")
      // this.parsedAccountData = JSON.parse(this.accountData)
      // console.log(this.parsedAccountData);
      // this.signInCheck = false
      this.userdata.setmsg(this.signInCheck)
      

    }
    else{
      this.router.navigate(['/home'])
    }
   }

  ngOnInit(): void {
  }
  getTransactions(){
    this.userdata.gettransactionlist().subscribe((data) =>{
      // console.log(data)
      let strin = JSON.stringify(data)
      let parsed = JSON.parse(strin)
      console.log(parsed);
      this.userdata.sendTransactionData(parsed)
    })

    
  }

}
