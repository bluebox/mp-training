import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';
import { UserInterface } from '../interface/user';


@Injectable({
  providedIn: 'root'
})
export class UserdataService {
  public url:string="http://127.0.0.1:8000/User/"

  constructor(private http:HttpClient ) {}
    getUsers(){
      return this.http.get<UserInterface[]>(this.url)
    }
    saveUser(data:any){
      return this.http.post(this.url,data)
    }
}
