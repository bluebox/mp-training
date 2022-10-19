import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { TeacherServiceService } from '../../teacher-service.service';
// import { DisplayCourseComponent } from '../display-course/display-course.component';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  formNotValid: boolean =false
  formError?: string =""
  teachers: any
  id:any
  course:any
  errmsg:string=''
  


  constructor(private http : TeacherServiceService, private router: Router,private actRouter:ActivatedRoute, private httpUser:HttpServiceService ) {

    this.actRouter.params.subscribe(data => {
      this.id = parseInt(data['id'])
      console.log('rakesh')
    })

    if(this.id){
      this.http.getCourse(this.id).subscribe({
        next:(resp:any)=>{
          this.course=resp.course
          this.courseAddingForm.get('course_name')?.setValue(this.course.course_name)
          this.courseAddingForm.get('total_marks')?.setValue(this.course.total_marks)
       
        }

      })
    }
   }


  courseAddingForm:FormGroup=new FormGroup({

    course_name: new FormControl("", Validators.required),
    total_marks: new FormControl("", Validators.required),


  })

  ngOnInit(): any {
    this.http.getTeachers().subscribe(data=>{
      this.teachers =data
      console.log(this.teachers)
    })
    if(this.id){

    this.http.getCourse(this.id).subscribe({
      next:(data:any)=>{
        console.log(data)
        this.course=data.course;
       
      },
      error:(err)=>{
        console.log(err.data)
      }
    })
  }

  }

  
  addCourse(){

    if(this.id){

      this.http.updateCourse(this.id, this.courseAddingForm.value).subscribe(data=>{
        console.log(data);
      })
      this.router.navigate(['teacher/display-course'])
    }

    else{
    console.log(this.courseAddingForm.value);
    this.http.AddCourse({ 'form': this.courseAddingForm.value, 'username': localStorage.getItem('username') }).subscribe(data=>{
      console.log(data);
      this.errmsg=data.message
      if(this.errmsg=='successful'){
        this.router.navigate(['teacher/display-course'])
      }
    })
    
  }
  }
}
