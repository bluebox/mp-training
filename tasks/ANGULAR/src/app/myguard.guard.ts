import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SharedService } from 'src/app/shared.service';

@Injectable({
  providedIn: 'root'
})
export class MyguardGuard implements CanActivate {
  constructor(private service: SharedService, private router: Router){};
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    //  let isloggedin=this.service.isloggedin()
    //  if(isloggedin)
    //     return true
    //  else
    //    this.router.navigate(["dashboard"])

    return true
  }


}
