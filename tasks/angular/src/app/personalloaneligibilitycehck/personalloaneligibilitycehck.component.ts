import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';
import { UserserviceService } from '../services/userservice.service';
import { Router } from "@angular/router"

@Component({
  selector: 'app-personalloaneligibilitycehck',
  templateUrl: './personalloaneligibilitycehck.component.html',
  styleUrls: ['./personalloaneligibilitycehck.component.css']
})
export class PersonalloaneligibilitycehckComponent implements OnInit {
loanStatus:any;
email:any;
  obj:any;
  status:any;
  delete_id:any;
  data:any
  response:any
  loan:any;
  Loanstatus:any;
  noloans:any;
constructor(private userdata:UserserviceService, private http:HttpClient, private router:Router) {
  if (localStorage.getItem("customer_refresh_token")) {
      this.email =  this.userdata.userDetails()
      this.http.post("api/loan_Status_data/", this.email).subscribe(data =>{
      // console.log(data)
      let str = JSON.stringify(data)
      let parse = JSON.parse(str)
      this.response = parse
      // console.log(this.response);
      
    
      if(this.response.loan_status === false){
        this.loanStatus = this.response
        this.noloans = "!No Active Loans"


      }
      else if (this.response === "No Active Loans"){
        this.noloans = this.response
      }
      else{
        this.http.post("api/loans_data/",this.email).subscribe(res =>{
          console.log(res);
          let string = JSON.stringify(res)
          let parsed = JSON.parse(string)
          
          this.loan = parsed

        })



      }

  })
    this.router.navigate(['/cusdashBoard/personalLoanEligibility']);
  }
  else {
    this.router.navigate(['/home']);
  }
 }

  ngOnInit(): void {
  }

  acceptLoan(){
    this.http.post("api/accept_loan/", this.loanStatus).subscribe(res =>{
      // console.log(res);
      this.Loanstatus = res
      let id = setTimeout(()=>{
        window.location.href = '/cusdashBoard/personalLoanEligibility/';
        clearTimeout(id)
      },2000)
    })
  }
  rejectLoan(){
    this.http.post("api/reject_loan/", this.loanStatus).subscribe(res =>{
      // console.log(res);
      this.Loanstatus = res
      let id = setTimeout(()=>{
        window.location.href = '/cusdashBoard/personalLoanEligibility/';
        clearTimeout(id)
      },2000)
    })

  }

}
