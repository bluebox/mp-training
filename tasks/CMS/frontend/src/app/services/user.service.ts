import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }
  isAuthenticated:boolean = false;

  loginUser!: {
    email: string;
    firstName: string;
    lastName: string;
    mobileNo: string;
    address: string;
    occupation: string;
  };


}
