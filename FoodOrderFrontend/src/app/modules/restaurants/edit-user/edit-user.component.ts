import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
res:any;
  constructor(public router:Router,public loginService:LoginService, public user:RestaurantService) { }

  ngOnInit(): void {
    this.loginCheck()
  }
  sigininForm=new FormGroup(
    {
  
      restaurant_id:new FormControl('', Validators.required),
      restaurant_name:new FormControl('', Validators.required),
      restaurant_address1:new FormControl('', Validators.required),
      restaurant_address2:new FormControl(''),
      restaurant_city:new FormControl('', Validators.required),
      restaurant_state:new FormControl('', Validators.required),
      restaurant_code:new FormControl('', Validators.required),
      restaurant_username:new FormControl('', Validators.required),
     
      restaurant_phn:new FormControl('', Validators.required),
      open_timing:new FormControl('', Validators.required),
      close_timing:new FormControl('', Validators.required),
      restaurant_photo:new FormControl('', Validators.required),
      // userPass2:new FormControl('', Validators.required)
    }
  )
  onSubmit(){ if (this.sigininForm.valid) {
      
    this.user.editUser(this.sigininForm.value,this.res.restaurant_id).subscribe((data)=>{
      console.log(this.res.restaurant_id)
     
    })
     alert("Registration Done!!")
     this.router.navigate(['/login'])

  } else {
    alert(" notttt form submitted ")
  }
  
  this.sigininForm.reset()}

  close(){this.ngOnInit()
    window.location.reload()
  }

  loginCheck(){
    this.loginService.loginCheck().subscribe((data)=>{
      console.log(data)
      this.res=data.body
      
      this.sigininForm.patchValue(data.body)
      
     
      
    })
    
  }}


