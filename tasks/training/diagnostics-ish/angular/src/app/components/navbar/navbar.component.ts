import { Component, OnInit } from '@angular/core';
import { SubjectServiceService } from 'src/app/services/subject-service/subject-service.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedIn : any
  isCustomer :boolean =false
  constructor(private subjectService: SubjectServiceService , private router :Router) { }

  ngOnInit(): void {
    this.subjectService.isLoggedInSubject.subscribe(data=>{
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

}
