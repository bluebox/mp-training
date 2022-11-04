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
  employeeName:any;
  email:any;
  customerCount:any;
  element:any;
  remainingElements:any;
  array:any[] = [1,2,3,4];
  number:any
  constructor(private router:Router, private userdata: UserserviceService, private http:HttpClient) { 
    
    if (localStorage.getItem("refresh_token")){
      this.router.navigate(['/empdashBoard']);
      this.loginAndLogOutCheck = false
      this.email= this.userdata.employeeDetails()
      this.http.post("api/get_employee/", this.email ).subscribe(name =>{
        this.employeeName = name
        console.log(this.employeeName);
      })

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

     this.http.get("api/customer_count/").subscribe(res=>{
      console.log(res);
      this.customerCount = res
     })
  }

  changeColor(num :any){
    // this.array = [1,2,3,4]
    // this.number = num
    // let id = "id" + String(this.number)
    // // console.log(id);
    // this.element = document.getElementById(id)
    // this.element.classList.add("text-style1")
    // let index = this.array.indexOf(num)
    // this.array.splice(index, 1)
    // console.log(this.array);
    // for (let each of this.array){

    //   let remaining = "id" + String(each)
    //   this.remainingElements = document.getElementById(remaining)
    //   this.remainingElements.classList.add("text-style")
    //   let ind = this.array.indexOf(each)
    //   ind = ind+1

    //   if (ind === this.array.length){
    //     this.element.classList.remove("text-style1")
    //     // this.array.push(this.number)
    //     console.log(this.array);
    //     break
        


    //   }
    //   else{
    //     continue
    //   }

    // }
    
    



  }

}
