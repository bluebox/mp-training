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
            console.log("uiefhrwuieb")
          this.route.navigate(['restaurants/main'])
         }
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
