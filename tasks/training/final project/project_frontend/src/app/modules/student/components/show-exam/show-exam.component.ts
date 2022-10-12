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

  constructor(private router:Router, private http: StudentServiceService) { }

  ngOnInit(): void {


    this.http.getCourses().subscribe({
      next:(resp)=>{
        this.courses=resp
        // console.log(this.courses);
        // for (var i of this.courses){
        //   console.log(i)
        //   localStorage.setItem('course_name',(i.course_name))
        //   console.log(localStorage.getItem('course_name'));
          
        // }
      }
      
    })


  }

  sendCourseName(arg:any){

    for (var i of this.courses){
      var val = {"course_name": arg}
       
      if (val.course_name==i.course_name){
        localStorage.setItem('course_name',(i.course_name))
        localStorage.setItem('course_id',(i.id))
        localStorage.setItem('total_marks',(i.total_marks))
        console.log(localStorage.getItem('course_name'));
        console.log(localStorage.getItem('course_id'));
        console.log(localStorage.getItem('total_marks'));

        this.http.sendCourse({'course_name':localStorage.getItem('course_name'),'course_id':localStorage.getItem('course_id'),'total_marks':localStorage.getItem('total_marks')}).subscribe(data=>{
          this.router.navigate(['student/attemptexam'])
          localStorage.setItem('question',JSON.stringify(data))
          
        })

      }
      
    }
    
  }

}
