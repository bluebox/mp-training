import { Injectable } from '@angular/core';
import {
  CanActivate,
  Router,
} from '@angular/router';
import axios from 'axios';


@Injectable({
  providedIn: 'root',
})
export class AuthgaurdGuard implements CanActivate {
  token_status: any;
  is_authenticated:boolean =false;
  constructor(
    
    private route: Router
  ) {
    
  }
  async canActivate() {
    
    let token = localStorage.getItem('refresh_token')
    try{
      let res = await axios.get('http://127.0.0.1:8000/check-refresh-token?refresh_token='+token)
      return res.data.is_admin
    }
    catch(e){
      this.route.navigate(['user/login'])
      return false
    }
  }

}
