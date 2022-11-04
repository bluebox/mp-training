import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {Router} from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-creditcards',
  templateUrl: './creditcards.component.html',
  styleUrls: ['./creditcards.component.css']
})
export class CreditcardsComponent implements OnInit {
email_id:any;
signInCheck:Boolean = true;
customerCheck:Boolean = true;
loginAndLogOutCheck:any;
email:any
response:any;
  constructor(private route:ActivatedRoute, private router:Router, private userdata:UserserviceService, private http:HttpClient) { 
    if (localStorage.getItem("refresh_token")){
     this.route.params.subscribe(email =>{
      console.log(email["email"])
      this.email_id = email["email"]
      this.CreditCardForm.get("customer_email")?.setValue(this.email_id)
    })
      this.loginAndLogOutCheck = false
    this.userdata.loginStatus(this.loginAndLogOutCheck)
    }
    else{
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
    
   
  }

  CreditCardForm = new FormGroup({
    customer_email : new FormControl(''),
    card_number :new FormControl(''),
    card_amount : new FormControl(''),
    cvv :new FormControl(''),
    expire_date: new FormControl('')
  })

  CreditCard(){

    this.http.post("api/credit_create/", this.CreditCardForm.value).subscribe(res=>{
      console.log(res);
      this.response = res
      let id = setTimeout(()=>{
        window.location.href = '/empdashBoard/employeerequest/';
        clearTimeout(id)
      },3000)
      
      
    })
  }


}
