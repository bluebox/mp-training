import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  isAdmin:boolean = false

  constructor(private auth: AuthService) {

  }


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      if(this.auth.isAuthenticated){
        this.auth.getAccessToken().subscribe(data=>{
          let dataString = JSON.stringify(data)
          let dataObj = JSON.parse(dataString)
          if(dataObj.token){
            this.auth.getUserDetails(dataObj.token).subscribe(data=>{
              let resString = JSON.stringify(data)
              let resObj = JSON.parse(resString)
              if(resObj){
                console.log(resObj.isAdmin);
                // this.isAdmin = resObj.isAdmin
                console.log("true");
                return resObj.isAdmin;
              }
            })
          }
        })
      }
      console.log("false");
      return true;

  }

}
