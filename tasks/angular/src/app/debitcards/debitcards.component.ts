import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-debitcards',
  templateUrl: './debitcards.component.html',
  styleUrls: ['./debitcards.component.css']
})
export class DebitcardsComponent {
  id: any;
  signInCheck: Boolean = true;
  customerCheck: Boolean = true;
  loginAndLogOutCheck: any;
  email: any
  email_id:any;
  emailObject: any
  account_id: any;
  response:any;
  constructor(private route: ActivatedRoute, private router: Router, private userdata: UserserviceService, private http: HttpClient) {

    if (localStorage.getItem("refresh_token")) {
      this.email = this.userdata.userDetails()

      // this.router.navigate(["/empdashBoard/employeerequest/debitcard", this.email])
      this.route.params.subscribe(email => {
        this.email_id = email["email"]
        this.DebitCardForm.get("customer_email")?.setValue(this.email_id)
      })

      this.loginAndLogOutCheck = false
      this.userdata.loginStatus(this.loginAndLogOutCheck)
    }
    else {
      this.router.navigate(['/home']);
    }
    // this.route.params.subscribe(email => {
    //   this.email_id = email["email"]
    //   this.DebitCardForm.get("customer_email")?.setValue(this.email_id)
    // })
  }







  ngOnInit(): void {
    
  }

  DebitCardForm = new FormGroup({
    customer_email: new FormControl(''),
    card_number: new FormControl(''),
    cvv: new FormControl(''),
    expire_date: new FormControl('')
  })

  CreateDebitCard() {
    this.http.post("api/debit_create/", this.DebitCardForm.value).subscribe(res => {
      console.log(res);
      this.response = res
      let id = setTimeout(()=>{
        window.location.href = '/empdashBoard/employeerequest/';
        clearTimeout(id)
      },3000)
    })
  }

}

