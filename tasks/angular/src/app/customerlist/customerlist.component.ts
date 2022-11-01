import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router} from '@angular/router'
import { UserserviceService } from '../services/userservice.service';


@Component({
  selector: 'app-customerlist',
  templateUrl: './customerlist.component.html',
  styleUrls: ['./customerlist.component.css']
})
export class CustomerlistComponent{
  signInCheck:Boolean = true;
  customerCheck:Boolean = true;
  loginAndLogOutCheck:any;
  count:any;
  employeeName:any;
  email:any;
  customerList:any
  error:any;
  status:any;
  constructor(private router:Router, private userdata: UserserviceService, private http:HttpClient) { 
    
    if (localStorage.getItem("refresh_token")){
      this.router.navigate(['/empdashBoard/customerlist']);
      this.loginAndLogOutCheck = false
      this.userdata.loginStatus(this.loginAndLogOutCheck)
      this.http.get("api/customer_list/").subscribe(list=>{
        let stingifi = JSON.stringify(list)
        let parsed = JSON.parse(stingifi)
        this.status = true
        this.customerList = parsed
       
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
    this.http.post("api/get_data_by_search/", {"text":text}).subscribe(res=>{
      console.log(res);
      let stingifi = JSON.stringify(res)
        let parsed = JSON.parse(stingifi)
        this.customerList = parsed
        this.status = true
        if (this.customerList.length === 0){
          this.error = "No Data Found"
          this.status = false

        }
        else if(text === ""){
          this.customerList = parsed
        
          this.error = ""

        }
        else{
          this.customerList = parsed

        }
        

    })

  }


}
