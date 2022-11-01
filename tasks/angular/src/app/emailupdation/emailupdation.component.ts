import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-emailupdation',
  templateUrl: './emailupdation.component.html',
  styleUrls: ['./emailupdation.component.css']
})
export class EmailupdationComponent implements OnInit {

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
      // this.router.navigate(["/empdashBoard/employeerequest/changeEmail"])
      this.email = this.userdata.userDetails()

      
      this.route.params.subscribe(email => {
        this.email_id = email["email"]
        this.ChangeEmailForm.get("customer_email")?.setValue(this.email_id)
      })
      

      this.loginAndLogOutCheck = false
      this.userdata.loginStatus(this.loginAndLogOutCheck)
    }
    else {
      this.router.navigate(['/home']);
    }
  }







  ngOnInit(): void {
    
  }

  ChangeEmailForm = new FormGroup({
    customer_email: new FormControl(''),
    new_email: new FormControl('')
  })

  ChangeEmail(){
    
    this.http.post("api/change_email/", this.ChangeEmailForm.value).subscribe(res=>{
      this.response = res
    })
    let id = setTimeout(()=>{
      window.location.href = '/empdashBoard/employeerequest/';
      clearTimeout(id)
    },3000)

  }
}
