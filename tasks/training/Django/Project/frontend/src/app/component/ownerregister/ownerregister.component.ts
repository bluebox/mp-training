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

  submit !: boolean
  response : any
  owner_group!: FormGroup
  error: string=''
  constructor(private service : GeneralService,private route:Router) { }

  ngOnInit(): void {
    this.owner_group = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z]{4,30}$')]),
      contact_no : new  FormControl('', Validators.required),
      email : new FormControl('', [Validators.email, Validators.required]),
      password : new FormControl('', [Validators.pattern('^[a-zA-Z]{8,20}$'), Validators.required]),
      password2 : new FormControl('', [Validators.pattern('^[a-zA-Z]{8,20}$'), Validators.required]),
      address : new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z]{5,30}$')], )
    })
}
register_owner(){

  let userObj = this.owner_group.value
  if(userObj.password == userObj.password2){
    delete userObj.password2
    this.service.registerOwner(userObj).subscribe((data : any) =>{(this.response=data)
    this.route.navigate(['ownerlogin']), alert('Registration successfull')}
      ,(err) => {alert('Invalid details')} )}

  else{
    this.error = "password did not match"
    console.log("miss match")
  }
}
get formData(){
  return this.owner_group.controls
}
}
