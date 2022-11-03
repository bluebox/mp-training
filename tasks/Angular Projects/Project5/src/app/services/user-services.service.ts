import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
// import localStorage from 'local-storage';
import { BehaviorSubject } from 'rxjs';
import { map, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})

export class UserServicesService {

    userData:any;
  is_Authenticated=false
  // private _is_loggedin =new BehaviorSubject<boolean>(false);
  // is_loggedin= this._is_loggedin.asObservable();
  private  headers={
    headers:{
      'Authorization':'Bearer '+localStorage.getItem('mytoken'),
    }
  }
  public token:string;
  public getToken(){
    return localStorage.getItem('mytoken')||'';
  }
  public setToken(value:string){
   return this.token=value
  }
  constructor( private http:HttpClient, private route:Router) { 
    this.token=localStorage.getItem('mytoken')||'';
  }
  getData( ){
    return this.http.get('http://127.0.0.1:8000/1')
  }
  getDataSingle(data:string ){
    return this.http.get('http://127.0.0.1:8000/1')
  }
  sendData( data:any){
    return this.http.post('http://127.0.0.1:8000/modelview/viewmodelset/', data)
  }
  login(data:any){
    return this.http.post('http://127.0.0.1:8000/login/', data).pipe(tap((response:any)=>{
      console.log(response.token);
    }))
  }
  register(data:any){
    return this.http.post('http://127.0.0.1:8000/register/',data)
  }

  loadUser(){
    
    return this.http.get('http://127.0.0.1:8000/profile/', this.headers).pipe(map((apiResponse:any) =>{
      console.log(apiResponse)
      return apiResponse;
      
    }))
  }
  logout() {
    return this.http.get('http://127.0.0.1:8000/profile/logout', this.headers)
    
  }
  getToken1(){
    return localStorage.getItem('mytoken')||'';
  }
  hasAccessToken(){
    return  localStorage.getItem('mytoken')!= null;
    
  }


verifyToken(){
    return this.http.post('http://127.0.0.1:8000/verifytoken/',this.headers)
  }
// login from utube
//   proceedlogin(usercredential:any){
//     this.http.post('http://127.0.0.1:8000/
//   }



private  headersrefresh={
    headers:{
      'Authorization':'Bearer '+localStorage.getItem('refresh_token'),
    }
  }
logout1(){
  console.log('session got expired');
  localStorage.clear();
  this.route.navigate(['user/login']);
}

}
