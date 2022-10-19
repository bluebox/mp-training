import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ViewComponent } from './info/components/view/view.component';
import { UserInterface } from './interface/user';
import { LoginComponent } from './login/login.component';
import { AuthService } from './services/auth.service';
import { UserdataService } from './services/userdata.service';
import { SignupComponent } from './signup/signup.component';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  public user!:UserInterface
  constructor(private auth:UserdataService,private dialog:MatDialog){
    this.getuser()
  }
  
  getuser(){
    this.auth.getuser().subscribe(data=>this.user=data)
  }
  canActivate(){
   
    if(this.auth.getstatus()){
    return true
    }
    else{
      this.dialog.open(SignupComponent)
      return false
    }
  }
  
}
