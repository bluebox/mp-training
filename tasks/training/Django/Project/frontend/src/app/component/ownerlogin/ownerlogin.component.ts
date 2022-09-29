import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Route, Router } from '@angular/router';
@Component({
  selector: 'app-ownerlogin',
  templateUrl: './ownerlogin.component.html',
  styleUrls: ['./ownerlogin.component.css']
})
export class OwnerloginComponent implements OnInit {
  login! : FormGroup;
  response: any
  constructor(private service : GeneralService, private route: Router) { }

  ngOnInit(): void {
    this.login= new FormGroup({
      email : new FormControl('' ),
      password : new FormControl('')
      })
    }

    ownerLogin(){
      console.log('here')
      this.service.ownerLogin(this.login.value).subscribe((data : any) =>{(this.response=data)
        window.sessionStorage.setItem('owner_id',JSON.stringify(data));this.route.navigate(['owner-profile'])}
        , (err) => {alert('Invalid credentials')},  )}

}
