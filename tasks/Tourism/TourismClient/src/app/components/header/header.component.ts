import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbOffcanvas, NgbOffcanvasConfig } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAuthenticated: boolean = false
  currentUser: any
  authenticationSubscription!: Subscription;
  loginUserSubscription!: Subscription;

  constructor(private auth: AuthService, private route: Router,
    private activatedRoute: ActivatedRoute,
    config: NgbOffcanvasConfig,
    private offcanvasService: NgbOffcanvas
    ) {
      config.position = 'end';
      // config.backdropClass = 'bg-info';
      config.keyboard = false;
  }

  updateIsActive(e:any){
    console.log(e);
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(data => {
      this.authenticationSubscription = this.auth.isAuthenticated.subscribe(res => {
        this.isAuthenticated=res
        // console.log(this.auth.currentUser);
        // this.currentUser = this.auth.currentUser
      })
      this.loginUserSubscription = this.auth.currentLoginUser.subscribe(user => {
        console.log(user);
        this.currentUser = user
      })
    })


  }

  open(content: any) {
    this.offcanvasService.open(content);
  }

  editProfile() {

  }

  submitLogout(){
    console.log("logout");
    this.auth.logout().subscribe(data => {
      console.log(data);
      if(data){
        console.log(data);
        // this.auth.changeAuthentication();
        // offcanvas.close('Close click')
        this.route.navigate(['login'])
      }
    })

  }

  ngOnDestroy() {
    if(this.loginUserSubscription){
      this.loginUserSubscription.unsubscribe()
    }
    if(this.authenticationSubscription){
      this.authenticationSubscription.unsubscribe()
    }
  }

}
