import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-student-navbar',
  templateUrl: './student-navbar.component.html',
  styleUrls: ['./student-navbar.component.css']
})
export class StudentNavbarComponent implements OnInit {
  
menuItems = ['dashboard', 'exams'];

  constructor(private router:Router, private http:StudentServiceService) { }

  ngOnInit(): void {
  }

  click1(){
    this.router.navigate(['student'])
  }
  click2(){
    this.router.navigate(['student/showexam'])
  }

}
