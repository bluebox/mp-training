import { Component, OnInit } from '@angular/core';
import { GeneralService } from 'src/app/general.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-customerregister',
  templateUrl: './customerregister.component.html',
  styleUrls: ['./customerregister.component.css']
})
export class CustomerregisterComponent implements OnInit {

  customer_group!: FormGroup
  error: string=''
  constructor(private service : GeneralService,private route:Router) { }


  ngOnInit(): void {
    this.customer_group = new FormGroup({
      name: new FormControl('', Validators.minLength(4)),
      contact_no : new  FormControl('', Validators.required),
      email : new FormControl('', Validators.email),
      dl_no : new FormControl('', Validators.required),
      password : new FormControl('', Validators.minLength(8)),
      password2 : new FormControl('', Validators.minLength(8)),
      address : new FormControl('', Validators.maxLength(100), )
    })
  }
  register_customer(){

    let userObj = this.customer_group.value
    if(userObj.password == userObj.password2){
      delete userObj.password2
      this.service.register_customer(userObj).subscribe(data=> console.log(data))
      this.route.navigate(['customerlogin'])

    }
    else{
      this.error = "password did not match"
      console.log("miss match")
    }
  }
}
