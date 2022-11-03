import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserServicesService } from '../services/user-services.service';

@Injectable({
  providedIn: 'root'
})
export class 
AuthGuardGuard implements CanActivate {
  constructor(private userService:UserServicesService,private route:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const is_Authorized:boolean=this.userService.getToken()?true:false
    if (!is_Authorized){
      this.route.navigate(['user/login'])
    }
    
      return is_Authorized;
  }
  
}
