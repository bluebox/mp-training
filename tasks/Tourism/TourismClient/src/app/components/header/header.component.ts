import { Component, OnInit } from '@angular/core';
import {
  ActivatedRoute,
  NavigationStart,
  Router,
  Event as NavigationEvent,
} from '@angular/router';
import { NgbOffcanvas, NgbOffcanvasConfig } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  isAuthenticated: boolean = false;
  currentUser: any;
  authenticationSubscription!: Subscription;
  loginUserSubscription!: Subscription;

  constructor(
    private auth: AuthService,
    private route: Router,
    private activatedRoute: ActivatedRoute,
    config: NgbOffcanvasConfig,
    private offcanvasService: NgbOffcanvas
  ) {
    config.position = 'end';
    // config.backdropClass = 'bg-info';
    config.keyboard = false;
  }

  activeRoute = 'Home';
  event$: any;

  // updateIsActive(e:any){
  //   // console.log(e);
  //   this.activeRoute = e.target.textContent
  //   console.log(this.activeRoute);
  // }

  ngOnInit(): void {
    this.event$ = this.route.events.subscribe((event: NavigationEvent) => {
      if (event instanceof NavigationStart) {
        let paths = event.url.split('/');
        this.activeRoute = paths[paths.length - 1];
      }
    });
    this.activatedRoute.params.subscribe((data) => {
      this.authenticationSubscription = this.auth.isAuthenticated.subscribe(
        (res) => {
          this.isAuthenticated = res;
          // console.log(this.auth.currentUser);
          // this.currentUser = this.auth.currentUser
        },
        err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
      );
      this.loginUserSubscription = this.auth.currentLoginUser.subscribe(
        user => {
          this.currentUser = user;
        },
        err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
      );
    });
  }

  open(content: any) {
    this.offcanvasService.open(content);
  }

  editProfile() {}

  submitLogout() {
    console.log('logout');
    this.auth.logout().subscribe((data) => {
        console.log(data);
        if (data) {
          console.log(data);
          // this.auth.changeAuthentication();
          // offcanvas.close('Close click')
          this.route.navigate(['login']);
        }
      },
      err => {
        if(err.status == 404){
          alert(err.message)
        }
        else{
          alert(err.error.detail)
        }
      }
    );
  }

  ngOnDestroy() {
    if (this.loginUserSubscription) {
      this.loginUserSubscription.unsubscribe();
    }
    if (this.authenticationSubscription) {
      this.authenticationSubscription.unsubscribe();
    }
    if (this.event$) {
      this.event$.unsubscribe();
    }
  }
}
