import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  cusId:any;
  constructor(private user:LoginService,private route:Router) { }

  ngOnInit(): void {
  }
  sigininForm=new FormGroup(
    {
      
      customer_password:new FormControl('', Validators.required),
      customer_email:new FormControl('', Validators.required),
      // userPass2:new FormControl('', Validators.required)
    }
  )



  onSubmit()
  {
    if (this.sigininForm.valid) {
      this.user.postUser(this.sigininForm.value).subscribe((data)=>{
        if(data.body == "login success"){
          this.route.navigate(['../'])
        }
        // alert("Registration Done!!")
      })
      this.user.loginCheck().subscribe(data=>{
        console.log(data);this.cusId=data
      ;console.log(this.cusId)})
        alert("Registration Done!!")
        console.log('form submitted');
        console.log(this.sigininForm.value)
      }
    
    else {
      console.log(' notttt form submitted');
      // alert(" notttt form submitted ")
    }
    
    this.sigininForm.reset()
  }

}

