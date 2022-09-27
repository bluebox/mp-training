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
  constructor(private fb:FormBuilder,private service : ServiceService) { }

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
    this.service.ClientRegistration(this.clientRegister.value).subscribe((data : any) => console.log(data)
    );
    console.log(this.clientRegister.value);
   }

}
