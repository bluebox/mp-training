import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAuthenticated: boolean = false

  constructor(private auth: AuthService, private route: Router) {
  }

  ngDoCheck(){
    this.isAuthenticated = this.auth.isAuthenticated
    // if(this.isAuthenticated){
    //   this.auth.getUserDetails().subscribe(data=>{
    //     let userString = JSON.stringify(data)
    //     let userObj = JSON.parse(userString)
    //     this.loginUser = userObj;
    //   })
    // }
  }

  ngOnInit(): void {
  }

  submitLogout(){
    console.log("logout");
    this.auth.logout().subscribe(data => {
      console.log(data);
      if(data){
        console.log(data);
        this.auth.changeAuthentication();
        this.route.navigate(['login'])
      }
    })

  }

}
