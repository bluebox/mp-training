import { Injectable } from '@angular/core';
import { UserdataService } from './userdata.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private user:UserdataService) { }
  isLoggedIn(){

    return true
  }
}
