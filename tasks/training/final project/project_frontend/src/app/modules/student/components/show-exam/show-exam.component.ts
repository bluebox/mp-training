import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-show-exam',
  templateUrl: './show-exam.component.html',
  styleUrls: ['./show-exam.component.css']
})
export class ShowExamComponent implements OnInit {

  displayedColumns: string[]=['course_name','exam']
  courses:any
  course_name:any

  constructor(private router:Router, private http: StudentServiceService) { }

  ngOnInit(): void {

    this.http.getCourses().subscribe({
      next:(resp)=>{
        this.courses=resp
        
      }
      
    })

  }

  sendCourseName(arg:any){

    for (var i of this.courses){
      
      var val = {"course_name": arg}
      console.log(val.course_name)
      this.course_name=val.course_name
      console.log(arg)
      this.router.navigate(['student/attemptexam',arg])
       
    
      
    }
    
  }

}
