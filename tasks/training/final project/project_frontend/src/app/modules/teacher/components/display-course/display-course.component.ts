import { Component, OnInit, ViewChild } from '@angular/core';
import { TeacherServiceService } from '../../teacher-service.service';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

export interface UserData {

}

@Component({
  selector: 'app-display-course',
  templateUrl: './display-course.component.html',
  styleUrls: ['./display-course.component.css']
})
export class DisplayCourseComponent implements OnInit {
  

  constructor(private http : TeacherServiceService) { }

  displayedColumns: string[]=['course_name', 'total_marks','Action']
  courses: any


  ngOnInit(): void {

    this.http.getCourses().subscribe({
      next:(resp)=>{
        this.courses=resp
       
        console.log(this.courses) 
      }
      
    })
  }
 
  deleteCourse(arg: any) {
    var val = {"id": arg}
    console.log(val)
    this.http.deleteCourses(val).subscribe(res=>{
      alert(res.toString())
      console.log(val)
      window.location.reload();
    })
  }

  
}
