import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css']
})
export class EmployeeDashboardComponent implements OnInit {
  signInCheck:Boolean = true;
  customerCheck:Boolean = true;
  loginAndLogOutCheck:any;
  count:any;
  constructor(private router:Router, private userdata: UserserviceService, private http:HttpClient) { 
    
    if (localStorage.getItem("refresh_token")){
      this.router.navigate(['/empdashBoard']);
      this.loginAndLogOutCheck = false
    this.userdata.loginStatus(this.loginAndLogOutCheck)
    }
    else{
      this.router.navigate(['/home']);
    }


  }

  ngOnInit(): void {
     this.userdata.setmsg(this.signInCheck)
     this.userdata.employeesignin(this.customerCheck)
     this.http.get("api/number_of_requests/").subscribe(res=>{
      console.log(res);
      this.count = res
     })
  }

}
