import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserserviceService } from '../services/userservice.service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-moneytransfer',
  templateUrl: './moneytransfer.component.html',
  styleUrls: ['./moneytransfer.component.css']
})
export class MoneytransferComponent{
errorMsg:any;
benificiarydata:any;
token:any;
color:any;
response:any;
  constructor(private userdata: UserserviceService, private router:Router) { 
    if (localStorage.getItem("customer_refresh_token")) {
      this.router.navigate(['/cusdashBoard/moneytransfer']);
    }
    else {
      this.router.navigate(['/home']);
    }

  }

  ngOnInit(): void {
  }
  MoneyTransferForm = new FormGroup({
    account_number: new FormControl(''),
    confirm_account_number :new FormControl(''),
    amount : new FormControl(''),
    customer_name : new FormControl('')


  })
  
  sendMoney(){
    if (this.MoneyTransferForm.value.account_number === this.MoneyTransferForm.value.confirm_account_number){
     
      this.token = this.getUserDetails()
      let userDetails;
      if (this.token) {
        userDetails = this.token.split(".")[1];
        userDetails = window.atob(userDetails);
        userDetails = JSON.parse(userDetails)

      } 
      else {
      
      }

      this.benificiarydata = {"account_number":this.MoneyTransferForm.value.account_number,
                              "amount":this.MoneyTransferForm.value.amount,
                              "receiver_name":this.MoneyTransferForm.value.customer_name,
                              "customer_email":userDetails.emp_id}
                              console.log(this.benificiarydata);
      this.userdata.moneyTransfer(this.benificiarydata).subscribe((res)=>{
        console.log(typeof(res))
        let stingfi = JSON.stringify(res)
        let parsing = JSON.parse(stingfi)
        console.log((typeof(parsing)));
        this.response = parsing
        // if (parsing == "Transaction Successful"){
        //   this.color = "green"
          
        // }
        // else{
        //   this.color = "red"
        // }

      })
    }
    else{
      this.errorMsg = "Please enter valid account number"
    }
  }
  getUserDetails(){
    return localStorage.getItem("customer_refresh_token")


  }


}
