import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { RegisterService } from '../services/register.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private service:RegisterService, private route: Router) {
  }

  canActivate() : boolean
    {
    if (this.service.doLogin()) {
      return true;
    }
    else {
      this.route.navigate(['/login'])
      return false
    }
  }
  
}
