import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../services/userservice.service';
import {Router} from "@angular/router"
import {CustomerdashboardComponent} from '../customerdashboard/customerdashboard.component'
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent  {
transactionData:any;
data:any;
id:any;
json:any;
senderDetails:any
  constructor(private userdata:UserserviceService, private router:Router, private http:HttpClient) { 
    if (localStorage.getItem("customer_refresh_token")) {
  
        
       let user_email = this.userdata.userDetails()
        this.userdata.gettransactionlist(user_email).subscribe((data) =>{
      
          let strin = JSON.stringify(data)
          let parsed = JSON.parse(strin)
          this.transactionData = parsed
        })
        this.router.navigate(['/cusdashBoard/transactionlist'])
        
  
      }
      else{
        this.router.navigate(['/home'])
  
  }
}

  ngOnInit(){
    
    
  }
  getUser(num:any){
    
    
    this.id = num 
    this.http.get(`api/user_details/${this.id}`).subscribe((data)=>{
      this.senderDetails = data
      alert(`Name:  ${this.senderDetails.customer_name}\nPhone Number:  ${this.senderDetails.phone_number}`)
    })

}
}
