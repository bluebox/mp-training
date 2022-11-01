import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import axios from 'axios';
import { Observable } from 'rxjs';
import { AppserviceService } from 'src/app/services/appservice.service';
import { FacilityService } from '../facilities/services/facility.service';

@Injectable({
  providedIn: 'root',
})
export class UseraccountgaurdGuard implements CanActivate {

  
  constructor(
    
    private router: Router,
  ) {
    
  }
  async canActivate() {
    
    let token = localStorage.getItem('refresh_token')
    try{
      let res = await axios.get('http://127.0.0.1:8000/check-refresh-token?refresh_token='+token)
      return !res.data.is_admin
    }
    catch(e){
      this.router.navigate(['user/login'])
      return false
    }
  }
}
