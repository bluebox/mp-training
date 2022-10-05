import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  constructor(private route: Router,
    private auth: AuthService
    ) {
      if(!this.auth.isAuthenticated){
        this.route.navigate(['login'])
      }
    }

  // token!:string

  ngOnInit(): void {
    // this.auth.getAccessToken().subscribe(data => {
    //   let res = JSON.stringify(data)
    //   let tokenObj = JSON.parse(res)
    //   console.log(tokenObj.token);
    //   // this.token = tokenObj.token;
    //   this.postFeed(tokenObj.token)

    // })
  }

  // postFeed(token: string) {
  //   if(token){
  //     this.auth.postFeedback(token).subscribe(data => {
  //       console.log(data);
  //     })
  //   }
  // }

  ngOnChanges() {

  }

  // submitLogout(){
  //   console.log("logout");
  //   this.auth.logout().subscribe(data => {
  //     console.log(data);
  //     if(data){
  //       console.log(data);
  //       this.route.navigate(['login'])
  //     }
  //   })
  // }

}
