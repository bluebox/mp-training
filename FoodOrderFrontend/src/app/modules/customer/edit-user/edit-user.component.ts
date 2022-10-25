import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  cus:any;
  constructor(public user:UserService, public router:Router,public loginService:LoginService) { }

  ngOnInit(): void {
    this.loginCheck()
    console.log(this.cus.customer_name)
  }
  sigininForm=new FormGroup(
    {
   
      customer_id:new FormControl('', Validators.required),
      customer_name:new FormControl('', Validators.required),
      customer_address1:new FormControl('', Validators.required),
      customer_address2:new FormControl(''),
      customer_city:new FormControl('', Validators.required),
      customer_state:new FormControl('', Validators.required),
      customer_code:new FormControl('', Validators.required),
      customer_username:new FormControl('', Validators.required),
      
      customer_phn:new FormControl('', Validators.required),
      
      // userPass2:new FormControl('', Validators.required)
  
    })

    onSubmit(){

     
      if (this.sigininForm.valid) {
      
        this.user.editUser(this.sigininForm.value,this.cus.customer_id).subscribe((data)=>{
          console.log(this.cus.customer_id)
         
        })
         alert("Registration Done!!")
         this.router.navigate(['/login'])

      } else {
        alert(" notttt form submitted ")
      }
      
      this.sigininForm.reset()

    }
    close()
    {
      this.ngOnInit()
      window.location.reload()
    }

    loginCheck(){
      this.loginService.loginCheck().subscribe((data)=>{
        console.log(data)
        this.cus=data.body
        
        this.sigininForm.patchValue(data.body)
        
       
        
      })
      
    }


}
