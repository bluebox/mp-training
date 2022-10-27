import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserdataService } from './services/userdata.service';

@Injectable({
  providedIn: 'root'
})

export class AntiAdminGuard implements CanActivate {
  constructor(private user:UserdataService){

  }
  canActivate(){
    if(this.user.role!='Admin'){
      console.log(this.user.permit)
      return true;
    }
    else{
      return false;
    }

  }
  
}
