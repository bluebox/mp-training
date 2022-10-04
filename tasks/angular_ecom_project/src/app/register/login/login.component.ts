import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataserveService } from 'src/app/dataserve.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private logindata: DataserveService, private route: Router) { }

data!:any
profileForm!:FormGroup;
  ngOnInit(): void {

  this.profileForm= new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(6),Validators.maxLength(8)]),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),


  });
  }


  onSubmit() {
    if (this.profileForm.valid){
    this.logindata.postCustLogin(this.profileForm.value).subscribe(newdata => {console.log(newdata)
    this.data = newdata
    this.logindata.isLoggedIn = true
    this.route.navigate(['/index']);
  },(error)=>{
    console.log(error)
  }
  )
    }
}

}
