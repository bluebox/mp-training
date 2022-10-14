import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserserviceService } from './services/userservice.service';
import {Router} from "@angular/router"

@Injectable({
  providedIn: 'root'
})
export class SecurityguardGuard implements CanActivate {
  constructor(private userdata:UserserviceService, private router:Router){}

  canActivate(){
    if (this.userdata.IsLogin()){
      return true

    }
    this.router.navigate(["/home"])
    return false;
  }
  
}
