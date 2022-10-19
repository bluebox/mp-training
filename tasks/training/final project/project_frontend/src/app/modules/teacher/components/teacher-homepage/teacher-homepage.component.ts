import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TeacherServiceService } from '../../teacher-service.service';

@Component({
  selector: 'app-teacher-homepage',
  templateUrl: './teacher-homepage.component.html',
  styleUrls: ['./teacher-homepage.component.css']
})
export class TeacherHomepageComponent implements OnInit {

  courses:any
  questions:any
  students:any
  i=0
  j=0
  k=0
  constructor(private router:Router, private http:TeacherServiceService) { }

  ngOnInit(): void {
    this.http.getCourses().subscribe({
      next:(resp)=>{
        this.courses=resp
       this.j=this.courses.length
        console.log(this.courses) 
      }

      
    })

    this.http.getQuestions().subscribe({
      next:(resp)=>{
        this.questions=resp
        this.i=this.questions.length
       
        console.log(this.questions.length) 
      }
      
    })
    this.http.getStudents().subscribe({
      next:(resp)=>{
        this.students=resp
        this.k=this.students.length
       
        console.log(this.students.length) 
      }
      
    })
  }

}
