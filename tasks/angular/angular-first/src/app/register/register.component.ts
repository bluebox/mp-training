import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ServiceService } from '../service.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  receivedData : string = ''
  clientRegister!: FormGroup;
  constructor(private fb:FormBuilder,private service : ServiceService,private router : Router) { }

  ngOnInit(): void {
    this.clientRegister = this.fb.group({
      'client_name'  : new FormControl ('',Validators.required),
      'email_id' : new FormControl ('',[Validators.required,Validators.email]),
      'phone_number'  : new FormControl ('',[Validators.required,Validators.maxLength(12)]),
      'client_country'  : new FormControl ('',[Validators.required]),
      'password' : new FormControl ('',[Validators.required,Validators.maxLength(25)]),
    })
  }
  registerSubmit(){
    this.service.ClientRegistration(this.clientRegister.value).subscribe((data : any) =>{ console.log(data);alert('Registered successfully') ; this.router.navigate(['login']);} , (err: any) =>{ alert("email already exists");});
    ;
    
   }


   get first_name(){
    return this.clientRegister.get('client_name');
  }

  get email_id(){
    return this.clientRegister.get('email_id');
  }

  get phone_number(){
    return this.clientRegister.get('phone_number');
  }

  get client_country(){
    return this.clientRegister.get('client_country');
  }

  get password(){
    return this.clientRegister.get('password');
  }
}
