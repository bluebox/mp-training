import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router} from '@angular/router'
import { UserserviceService } from '../services/userservice.service';


@Component({
  selector: 'app-customerloans',
  templateUrl: './customerloans.component.html',
  styleUrls: ['./customerloans.component.css']
})
export class CustomerloansComponent implements OnInit {
  signInCheck:Boolean = true;
  customerCheck:Boolean = true;
  loginAndLogOutCheck:any;
  count:any;
  employeeName:any;
  email:any;
  loansList:any
  status:any;
  error:any;
  constructor(private router:Router, private userdata: UserserviceService, private http:HttpClient) { 
    
    if (localStorage.getItem("refresh_token")){
      this.router.navigate(['/empdashBoard/customerloans']);
      this.loginAndLogOutCheck = false
      this.userdata.loginStatus(this.loginAndLogOutCheck)
      this.http.get("api/emp_loans_list/").subscribe(list=>{
        console.log(list);
        let stingifi = JSON.stringify(list)
        let parsed = JSON.parse(stingifi)
        this.status = true
        this.loansList = parsed
      })

    }
    else{
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
  }
  getData(text:any){
    console.log(text);
    this.http.post("api/get_data_by_search_loans/", {"text":text}).subscribe(res=>{
      console.log(res);
      let stingifi = JSON.stringify(res)
        let parsed = JSON.parse(stingifi)
        this.loansList = parsed
        this.status = true
        if (this.loansList.length === 0){
          this.error = "No Data Found"
          this.status = false

        }
        else if(text === ""){
          this.loansList = parsed
        
          this.error = ""

        }
        else{
          this.loansList = parsed

        }
        

    })

  }


}
