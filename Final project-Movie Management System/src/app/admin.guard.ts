import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserInterface } from './interface/user';
import { UserdataService } from './services/userdata.service';

@Injectable({
  providedIn: 'root'
})

  
  
  
 
export class AdminGuard implements CanActivate {
  public user!:UserInterface
  constructor(private auth:UserdataService){
    this.getuser()
  }
  getuser(){
    this.auth.getuser().subscribe(data=>this.user=data)
  }
  canActivate(){
    let r=this.auth.getrole()
    if(r==="Admin"){
      console.log(r)
      return true
    }

    else{
      console.log(r)
    alert("Access denied!")
    return false;
    }
  }
  
}
