import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserdataService } from 'src/app/services/userdata.service';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {
  Signup:FormGroup =new FormGroup({
    User_name: new FormControl("",Validators.required),
    User_email: new FormControl("",[Validators.required,Validators.email]),
    password: new FormControl("",[Validators.required]),
    Confirm_password: new FormControl("",[Validators.required]),
    User_phone: new FormControl("",[Validators.required,Validators.pattern('^[0-9]{10}')]),
    User_DOB:new FormControl("",[Validators.required]),
    User_Address:new FormControl("",[Validators.required]),
    Role:new FormControl("",Validators.required)
  })

  constructor(public user:UserdataService) { }

  ngOnInit(): void {
  }

  OnSubmit(data:any){
  
    if(this.Signup.value.password===this.Signup.value.Confirm_password){
      console.log("YAY")
      this.user.saveUser(this.Signup.value).subscribe(data=>console.log(data))
    }
    else{
      alert("Fill the form correctly!!")
    }
  }

}
