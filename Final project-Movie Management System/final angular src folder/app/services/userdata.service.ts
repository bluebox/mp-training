import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserInterface } from '../interface/user';


@Injectable({
  providedIn: 'root'
})
export class UserdataService {
  public status:boolean=false
  public url:string="http://127.0.0.1:8000/User/"
  public user!:UserInterface
  public role:string="User"
  public permit:boolean=false

  constructor(private http:HttpClient ) {}
    getUsers(){
      return this.http.get<UserInterface[]>(this.url)
    }
    saveUser(data:any){
      return this.http.post("http://127.0.0.1:8000/register",data)
    }
    userLogin(data:any){
      return this.http.post("http://127.0.0.1:8000/login",data, { withCredentials: true })
    }
    logout(){
      return this.http.post("http://127.0.0.1:8000/logout","",{ withCredentials: true })
    }
    getuser(){
      return this.http.get<UserInterface>("http://127.0.0.1:8000/getuser", { withCredentials: true })
    }
    setstatus(newvalue:boolean){
     return this.status=newvalue
    }
    getstatus(){
      return this.status
    }
    setrole(role:string){
      this.role=role
      if(role==='Admin' || role==='TheatreOwner'){
      this.permit=true}
    }
    getrole(){
      return this.role
    }
    setUser(data:any){
      this.user=data
      console.log(this.user)
    }


}
