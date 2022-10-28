import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Emitters } from 'src/app/emitters/emitters';
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
      
      password:new FormControl('', Validators.required),
      email:new FormControl('', Validators.required),
      // userPass2:new FormControl('', Validators.required)
    }
  )



  onSubmit()
  {
    if (this.sigininForm.valid) {
      this.user.postUser(this.sigininForm.value).subscribe((data)=>{
        console.log(data.body)  
        console.log(data.body.success)
          if(data.body.success=='True'){
            Emitters.authEvent.emit(true)
          this.route.navigate(['../']) }
          else{
            alert("Error!!")
          }
      })
   
      }
    
    else {
      console.log(' notttt form submitted');
      // alert(" notttt form submitted ")
    }
    
    this.sigininForm.reset()
  }

}

