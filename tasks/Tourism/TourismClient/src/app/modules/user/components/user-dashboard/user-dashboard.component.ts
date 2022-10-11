import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  constructor(private route: Router,
    private auth: AuthService,
    private activatedRoute: ActivatedRoute
    ) {
      // this.auth.isAuthenticated.subscribe(res => {
      //   console.log(res);
      //   if(!res)
      //     this.route.navigate(['login'])
      // })
  }
  subscription!: Subscription


  ngOnInit(): void {
    this.activatedRoute.params.subscribe(data => {
      this.subscription = this.auth.isAuthenticated.subscribe(
        res => {
          console.log(res);
          if(!res)
            this.route.navigate(['login'])
        },
        err => alert(err.error.detail)
      )
    })

  }

  ngOnDestroy() {
    if(this.subscription){
      this.subscription.unsubscribe()
    }
  }

}
