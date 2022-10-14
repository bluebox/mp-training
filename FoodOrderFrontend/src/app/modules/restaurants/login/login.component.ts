import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  empId:any;
  constructor(private user:LoginService,private route:Router) { }

  ngOnInit(): void {
  }

  sigininForm=new FormGroup(
    {
      
      res_password:new FormControl('', Validators.required),
      res_email:new FormControl('', Validators.required),
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
        console.log(data);this.empId=data
      ;console.log(this.empId)})
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
