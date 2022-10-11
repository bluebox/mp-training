import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  isAdmin:boolean = false
  isAthenticated!: boolean

  constructor(private auth: AuthService,
    private route: Router,
    private activatedRoute: ActivatedRoute
    ) {
      // console.log("guard");
      // this.auth.isAuthenticated.subscribe(res => this.isAthenticated = res)
      // console.log("guard");



      this.activatedRoute.params.subscribe(data => {
        console.log("guard params");
        this.auth.isAuthenticated.subscribe(res => {
          this.isAthenticated = res
        })
      })
  }

  ngOnInit() {
    console.log("guard");
    this.activatedRoute.params.subscribe(data => {
      this.auth.isAuthenticated.subscribe(res => this.isAthenticated = res)
    })
  }


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree
    {

      // return this.auth.checkIfUserAuthenticated().then(res => {
      //   return res;
      // })

      if(this.auth.isLogin){
        return true
      }
      else{
        this.route.navigate(['login'], {queryParams: { returnUrl: state.url}})
        return false
      }
  }

}
