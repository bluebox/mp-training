import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
isLoggedin :boolean = false; 
userdetails : any ;

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.userdetails=sessionStorage.getItem('userdetails')
  let  user=JSON.parse(this.userdetails)
      if(user){
        return true
      }
      return this.isLoggedin;
  }
  
}
