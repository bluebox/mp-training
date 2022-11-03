import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Resolve, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResolverGuard implements Resolve<any> {
  UserObj={id:10,name:"shyam"}
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.UserObj
  }
 
  
}
