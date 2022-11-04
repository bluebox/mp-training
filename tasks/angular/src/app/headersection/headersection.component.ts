import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../services/userservice.service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-headersection',
  templateUrl: './headersection.component.html',
  styleUrls: ['./headersection.component.css']
})
export class HeadersectionComponent{
signInCheck:Boolean = true
customerCheck:any;
data:any;
loginAndLogOutCheck:Boolean= true
  constructor(private userdata:UserserviceService, private router:Router) {

   

      if (localStorage.getItem("refresh_token")){
         this.signInCheck = true
         this.customerCheck = false
         this.loginAndLogOutCheck = false

      }
      else{
        this.signInCheck = false
        // this.customerCheck = false
        this.loginAndLogOutCheck = true
      }
    
   }

   ngDoCheck(): void{

     this.signInCheck = this.userdata.getmsg()
     this.customerCheck = this.userdata.customerlogout()
     this.loginAndLogOutCheck = this.userdata.getloginStatus()

   }
  
    
  


   local_token(){
     localStorage.removeItem('refresh_token');
     this.router.navigate(['/home']);
     this.signInCheck = false
    this.userdata.setmsg(this.signInCheck)
    this.loginAndLogOutCheck = false
    this.userdata.loginStatus(this.loginAndLogOutCheck)
  

}
customerlogout(){
  localStorage.removeItem('customer_refresh_token');
  localStorage.removeItem("customer_data")
  localStorage.removeItem("account_data")
  this.router.navigate(['/home']);
  this.signInCheck = false
  this.userdata.setmsg(this.signInCheck)
  this.loginAndLogOutCheck = true
    this.userdata.loginStatus(this.loginAndLogOutCheck)

}

}


