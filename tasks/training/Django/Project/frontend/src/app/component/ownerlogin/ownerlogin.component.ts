import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
@Component({
  selector: 'app-ownerlogin',
  templateUrl: './ownerlogin.component.html',
  styleUrls: ['./ownerlogin.component.css']
})
export class OwnerloginComponent implements OnInit {
  login! : FormGroup;
  response: any
  submit !: boolean
  constructor(private service : GeneralService, private route: Router) {
    // console.log(window.sessionStorage.getItem('token'))
    if (window.sessionStorage.getItem('owner_token')){
      route.navigate(['previousvehicles'])
    }
  }

  ngOnInit(): void {
    this.login= new FormGroup({
      email : new FormControl('', [Validators.required, Validators.email] ),
      password : new FormControl('', Validators.required )
      })
    }

    ownerLogin(){
      console.log('here')
      this.service.ownerLogin(this.login.value).subscribe((data : any) =>{(this.response=data)
        window.sessionStorage.setItem('owner_token',JSON.stringify(data));
        this.route.navigate(['previousvehicles'])}
        ,(err) => {alert('Invalid credentials')} )}

    get formData(){
      return this.login.controls
    }

}
