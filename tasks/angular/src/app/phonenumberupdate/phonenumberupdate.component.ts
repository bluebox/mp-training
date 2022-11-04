import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-phonenumberupdate',
  templateUrl: './phonenumberupdate.component.html',
  styleUrls: ['./phonenumberupdate.component.css']
})
export class PhonenumberupdateComponent implements OnInit {

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

      // this.router.navigate(["/empdashBoard/employeerequest/changePhoneNumber"])
      this.route.params.subscribe(email => {
        this.email_id = email["email"]
        this.ChangeNumberForm.get("customer_email")?.setValue(this.email_id)
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

  ChangeNumberForm = new FormGroup({
    customer_email: new FormControl(''),
    old_phone_number: new FormControl(''),
    new_number: new FormControl('')
  })
  ChangeNumber(){
    console.log(this.ChangeNumberForm.value);
    this.http.post("api/change_phone_number/", this.ChangeNumberForm.value).subscribe(res=>{
      this.response = res
    })
    let id = setTimeout(()=>{
      window.location.href = '/empdashBoard/employeerequest/';
      clearTimeout(id)
    },3000)

  }

}
