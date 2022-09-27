import { Component, OnInit } from '@angular/core';
import { GeneralService } from 'src/app/general.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ownerregister',
  templateUrl: './ownerregister.component.html',
  styleUrls: ['./ownerregister.component.css']
})
export class OwnerregisterComponent implements OnInit {

  owner_group!: FormGroup
  error: string=''
  constructor(private service : GeneralService,private route:Router) { }

  ngOnInit(): void {
    this.owner_group = new FormGroup({
      name: new FormControl('', Validators.minLength(4)),
      contact_no : new  FormControl('', Validators.required),
      email : new FormControl('', Validators.email),
      password : new FormControl('', Validators.minLength(8)),
      password2 : new FormControl('', Validators.minLength(8)),
      address : new FormControl('', Validators.maxLength(100), )
    })
}
register_owner(){

  let userObj = this.owner_group.value
  if(userObj.password == userObj.password2){
    delete userObj.password2
    this.service.register_owner(userObj).subscribe(data=> console.log(data))
    this.route.navigate(['ownerlogin'])

  }
  else{
    this.error = "password did not match"
    console.log("miss match")
  }
}
}
