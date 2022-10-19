import { Component, OnInit } from '@angular/core';
import { SubjectServiceService } from 'src/app/services/subject-service/subject-service.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedIn : any
  isCustomer :boolean =false
  isLoggenInSubscription!: Subscription;

  constructor(private subjectService: SubjectServiceService , private router :Router) { }

  ngOnInit(): void {
    this.isLoggenInSubscription= this.subjectService.isLoggedInSubject.subscribe(data=>{
      this.loggedIn = data
    })
    
  }
  navigateCustomer(){
    if(this.loggedIn){
      this.router.navigate(["customer/booking/"])
    }
    else{
      this.router.navigate(["login/"])
    } 
  }
  ngOnDestroy(){
    this.isLoggenInSubscription.unsubscribe()
}
}
