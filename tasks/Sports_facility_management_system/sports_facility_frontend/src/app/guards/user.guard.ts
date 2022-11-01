import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import axios from 'axios';
import { Observable } from 'rxjs';
import { AppserviceService } from '../services/appservice.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {
  constructor(
    // private Service: AppserviceService,
    
  ) {
    
  }
  async canActivate()
    // route: ActivatedRouteSnapshot,
    // state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree 
    {
    // return this.Service.is_user;
    let token = localStorage.getItem('refresh_token')
    let res = await axios.get('http://127.0.0.1:8000/check-refresh-token?refresh_token='+token)
    return res.data.is_admin
  }
  
}
