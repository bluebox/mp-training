import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Customer } from 'src/app/interfaces/customer';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  constructor(private user:UserService) { }

  ngOnInit(): void {
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
      customer_password:new FormControl('', Validators.required),
      customer_phn:new FormControl('', Validators.required),
      customer_email:new FormControl('', Validators.required),
      // userPass2:new FormControl('', Validators.required)
    }
  )



  onSubmit()
  {
    if (this.sigininForm.valid) {
      this.user.postUser(this.sigininForm.value).subscribe((data)=>{
        console.log(data)
      })
      console.log('form submitted');
      console.log(this.sigininForm.value)
    } else {
      console.log(' notttt form submitted');
    }
    
    this.sigininForm.reset()
  }
}