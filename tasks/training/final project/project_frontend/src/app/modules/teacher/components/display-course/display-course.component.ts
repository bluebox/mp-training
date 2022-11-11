import { Component, OnInit, ViewChild } from '@angular/core';
import { TeacherServiceService } from '../../teacher-service.service';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { AddCourseComponent } from '../add-course/add-course.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';



@Component({
  selector: 'app-display-course',
  templateUrl: './display-course.component.html',
  styleUrls: ['./display-course.component.css']
})
export class DisplayCourseComponent implements OnInit {


  displayedColumns: string[]=['s.no','course_name','total_marks','Action']
  courses: any
  result:any
  text:string=''
  v:boolean=true
  i:any

  constructor(private router:Router, private http : TeacherServiceService) { }

  ngOnInit(): void {

    this.http.getCourses().subscribe({
      next:(resp)=>{

        this.courses=resp
       
        console.log(this.courses) 
      }
      
    })
  }

 
  deleteCourse(arg: any) {
    if(confirm("do u want to delete")){
    var val = {"id": arg}
    console.log(val)
    this.http.deleteCourses(val).subscribe(res=>{
      alert(res.toString())
      console.log(val)
      window.location.reload();
    })
  }
  }

  updateCourse(id:any){
    this.router.navigate(['teacher/edit-course' , id])

  }
  add(){
    this.router.navigate(['teacher/courseregister'])
  }

  onSearchText() {
    console.log(this.text);
    this.http.getADist(this.text).subscribe({
      next:(res)=>{
        
        this.result= res;
        
        this.courses=res;
        console.log(res.length)
        if(res.length==0){

          this.v=false
        }
        else{
          this.v=true
        }
        console.log(this.result)
      }
    })
  }

}
