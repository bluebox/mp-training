import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TeacherServiceService } from '../../teacher-service.service';

@Component({
  selector: 'app-teacher-navbar',
  templateUrl: './teacher-navbar.component.html',
  styleUrls: ['./teacher-navbar.component.css']
})
export class TeacherNavbarComponent implements OnInit {

  constructor(private router:Router, private http:TeacherServiceService ) { }

  ngOnInit(): void {

   
  }

  click1(){
    this.router.navigate(['teacher'])
  }
  click2(){
    this.router.navigate(['teacher/display-course'])
  }
  click3(){
    this.router.navigate(['teacher/display-question'])
  }
  clickOut(){
    this.router.navigate([''])
  }

}
