import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Emitters } from 'src/app/emitters/emitters';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar-cus',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
authenticated=false
user:any;
message=""
  constructor(private loginService:LoginService,public router:Router) { }

  ngOnInit(): void {
    this.loginCheck()
    Emitters.authEvent.subscribe(
      (auth:boolean)=>{
        this.authenticated=auth
      }
    )
  }
  logout()
  {
    this.loginService.logout().subscribe(()=>{
      this.authenticated=false

      this.router.navigate([''])
      
    
     
    })
    
  }
  loginCheck(){
    this.loginService.loginCheck().subscribe((data)=>{
      console.log(data)
      this.user=data
      console.log("hwjeiwndwed")
      if(data.body.role=="customer")
      {
        console.log(data.body.role)
        // this.authenticated=true
        Emitters.authEvent.emit(true)
        console.log("--------------")
      }
      
    })
    
  }

  cart()
  {
    
  }
}
