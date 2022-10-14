import { Component, OnInit, SimpleChange } from '@angular/core';

import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserserviceService } from '../services/userservice.service';
import {Router} from '@angular/router'
import { window } from 'rxjs';

@Component({
  selector: 'app-employeepage',
  templateUrl: './employeepage.component.html',
  styleUrls: ['./employeepage.component.css']
})
export class EmployeepageComponent implements OnInit {
  response: any;
  emailError:any;
  passError:any;
  signInCheck:Boolean = true
  customerCheck:Boolean = true
  // obj:Object = {"signInCheck": true, "customerCheck": false}
 
  constructor( private userdata:UserserviceService, private router: Router) {
  
    
    if (localStorage.getItem("refresh_token")){
      this.router.navigate(['/empdashBoard']);
    }
     
    else{
      this.router.navigate(['./emppage/emplogin']);

    }

    
   }

  ngOnInit(): void {
    
  }
  

  empLoginForm = new FormGroup({
    emp_email: new FormControl('', [Validators.required, Validators.email]),
    emp_password: new FormControl('', [Validators.required, Validators.minLength(5)]),

  })

  get emp_email(){
    return this.empLoginForm.get("emp_email")

  }
  get emp_password(){
    return this.empLoginForm.get('emp_password')
  }
  
  empSignIn(){

    this.userdata.emploginapi(this.empLoginForm.value).subscribe((resp)=>{
      // let strin = JSON.stringify(resp)
      // let parse = JSON.parse(strin)
      console.log(resp);
      this.response = resp
      // console.log(document.cookie);
      if(this.response.refresh_token){
  
      localStorage.setItem("refresh_token", this.response.refresh_token)
      this.router.navigate(['/empdashBoard']);
      
      this.userdata.setmsg(this.signInCheck)
      this.userdata.employeesignin(this.customerCheck)
      }
  
      else{
        this.emailError = this.response.emp_email
        this.passError = this.response.emp_password

      }
    
    })
    
    
    

  }

}