import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, TitleStrategy, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SubjectServiceService } from '../services/subject-service/subject-service.service';

@Injectable({
  providedIn: 'root'
})

export class AdminGaurdGuard implements CanActivate {
  isAdmin : boolean = false
  loggenIn :boolean =false
  constructor(private subjectService : SubjectServiceService, private  route:Router){
    
    // this.subjectService.isLoggedInSubject.subscribe(data=>{
    //   this.loggenIn = data
    // })
  }

  verifyAdmin(){
    this.subjectService.userTypeSubject.subscribe(data => {
      console.log(data);

      if (data == 'admin') {
        this.isAdmin = true
      }
    })
  }
  ngOnInit(){
    
  }
  verifyLogin(){
    this.subjectService.userTypeSubject.subscribe(data => {
      if (data == 'admin') {
        this.isAdmin = true
      }
    })
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      this.verifyAdmin()
      this.verifyLogin()
      if(this.isAdmin){
        console.log("logged in gaurds");
        return true;
      }
      else{
        console.log("logged in gaurds");

        this.route.navigate(['login'], { queryParams: { returnUrl: state.url } })
        return false
      }
    
  }
  
}
