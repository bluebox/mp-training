import { Component, OnInit, ViewChild } from '@angular/core';
import { TeacherServiceService } from '../../teacher-service.service';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { AddCourseComponent } from '../add-course/add-course.component';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

export interface UserData {

}

@Component({
  selector: 'app-display-course',
  templateUrl: './display-course.component.html',
  styleUrls: ['./display-course.component.css']
})
export class DisplayCourseComponent implements OnInit {


  displayedColumns: string[]=['course_name', 'total_marks','Action']
  courses: any
  dataSource !: MatTableDataSource<any>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;
  

  constructor(private router:Router, private http : TeacherServiceService) { }

  ngOnInit(): void {

    this.http.getCourses().subscribe({
      next:(resp)=>{

        this.dataSource = new MatTableDataSource(resp);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.courses=resp
       
        console.log(this.courses) 
      }
      
    })
  }

  // editCourse(args: any) {
  //   const dialogRef = this.dialog.open(AddCourseComponent,{data:{id: args.id, name: args.course_name,status: args.total_marks }});

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log(`Dialog result: ${result}`);
  //     window.location.reload();
  //   });
  // }

  // addCourse() {
  //   const dialogRef = this.dialog.open(AddCourseComponent,{data:{id: 0, name: "",status: true }});

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log(`Dialog result: ${result}`);
  //     window.location.reload();
  //   });
  // }

 
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
