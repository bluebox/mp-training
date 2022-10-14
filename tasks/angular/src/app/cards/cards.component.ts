import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../services/userservice.service';
import { Router } from "@angular/router"

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {
  obj:any;
  debitData:any;
  creditData:any;
  name:any;
  debit:any;
  credit:any;
  constructor(private userdata:UserserviceService, private http:HttpClient, private router:Router) { 
    if (localStorage.getItem("customer_refresh_token")) {
      this.router.navigate(['/cusdashBoard/cards']);
      this.obj = this.userdata.userDetails()
      this.http.post("api/get_cards/",this.obj).subscribe(data =>{
      console.log(data);
      let strin = JSON.stringify(data)
      let parsed = JSON.parse(strin)
      this.debitData = parsed.debit_card
      this.creditData = parsed.credit_card
      this.name = parsed.name
      console.log(this.debitData);
      console.log(this.creditData);
      // console.log(debitData);
      if (this.debitData.status !== "No Debit Card" && this.creditData.status === "No Credit Card"){
        this.debit = true
        this.credit = false

      }
      else if (this.debitData.status === "No Debit Card" && this.creditData.status === "No Credit Card"){
        this.debit = false
        this.credit = false
      }
      else{
        this.debit = true
        this.credit = true
      }
    })
    }
    else {
      this.router.navigate(['/home']);
    }

    

  }

  ngOnInit(): void {
  }

}
