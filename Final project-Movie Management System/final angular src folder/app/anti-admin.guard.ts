import { Injectable } from '@angular/core';
import { CanActivate} from '@angular/router';
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
