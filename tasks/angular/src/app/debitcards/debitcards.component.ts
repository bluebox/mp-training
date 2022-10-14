import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {Router} from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-debitcards',
  templateUrl: './debitcards.component.html',
  styleUrls: ['./debitcards.component.css']
})
export class DebitcardsComponent{
  id:any;
  signInCheck:Boolean = true;
customerCheck:Boolean = true;
loginAndLogOutCheck:any;
email:any
emailObject:any
account_id:any;
  constructor(private route:ActivatedRoute, private router:Router, private userdata:UserserviceService, private http:HttpClient) {

          if (localStorage.getItem("refresh_token")){
            this.email = this.userdata.userDetails()
            this.emailObject = this.email
            this.http.post("api/get_account_id/", this.emailObject).subscribe(res => {
              this.account_id = res
              console.log( this.account_id);
              this.router.navigate(["/empdashBoard/employeerequest/debitcard", this.account_id])
            })
            this.route.params.subscribe(id =>{
              this.id = id["id"]
              this.DebitCardForm.get("account_number_id")?.setValue(this.id)
            })
          
            this.loginAndLogOutCheck = false
            this.userdata.loginStatus(this.loginAndLogOutCheck)
          }
          else{
            this.router.navigate(['/home']);
          }
  }





   

  ngOnInit(): void {
    this.route.params.subscribe(id =>{
      this.id = id["id"]
      this.DebitCardForm.get("account_number_id")?.setValue(this.id)
    })
  }

  DebitCardForm = new FormGroup({
    account_number_id : new FormControl(''),
    card_number :new FormControl(''),
    cvv :new FormControl(''),
    expire_date: new FormControl('')
  })

  CreateDebitCard(){
    
  }

}

