import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { UserServicesService } from './services/user-services.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit , OnChanges{



  title = 'Project5';

  constructor(private userService: UserServicesService, private router: Router){
  }
  ngOnInit(): void {
    return
  }
  ngOnChanges(changes: SimpleChanges): void {
   
}
is_Authenticated():Boolean {
   return this.userService.hasAccessToken()
  }
  logout() {
    // localStorage.removeItem('mytoken')
    // localStorage.removeItem('refresh_token')
    // localStorage.removeItem('remember')
    // localStorage.removeItem('email')


    // this.userService.logout().subscribe((apiResponse:any)=>{
    //   if(apiResponse && apiResponse.user_id>0){
    //     this.router.navigate(['/user/login'],{queryParams:{"msg":"successfully Logged"}})

    //   }

    // })

    this.userService.logout1();
    // return this.router.navigate(['/user/login'],{queryParams:{"msg":"successfully Logged"}});
    }
}