import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
      userId:new FormControl('', Validators.required),
      userName:new FormControl('', Validators.required),
      name:new FormControl('', Validators.required),
      userPhn:new FormControl('', Validators.required),
      userEmail:new FormControl('', Validators.required),
      userAddress: new FormGroup({
        userAddress1:new FormControl('', Validators.required),
        userAddress2:new FormControl(''),
        userCity:new FormControl('', Validators.required),
        userState:new FormControl('', Validators.required),
        userCode:new FormControl('', Validators.required)
      }),
      userPass:new FormControl('', Validators.required),
      userPass2:new FormControl('', Validators.required)
    }
  )


  onSubmit()
  {
    if (this.sigininForm.valid) {
      console.log('form submitted');
      console.log(this.sigininForm.value)
    } else {
      console.log(' notttt form submitted');
    }
    
    this.sigininForm.reset()
  }
}
