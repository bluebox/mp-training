import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-my-marks',
  templateUrl: './my-marks.component.html',
  styleUrls: ['./my-marks.component.css']
})
export class MyMarksComponent implements OnInit {
  displayedColumns: string[]=['course_name','exam']
  courses:any
  course_name:any

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
  sendMarks(arg:any){

    
    var val = {"course_name": arg}
    console.log(val.course_name)
    this.course_name=val.course_name
    console.log(arg)
    this.router.navigate(['student/checkmarks',arg])


  }


}


