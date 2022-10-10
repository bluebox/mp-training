import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TeacherServiceService } from '../../teacher-service.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  formNotValid: boolean =false
  formError?: string =""
  teachers: any


  constructor(private http : TeacherServiceService, private router: Router ) { }

  courseAddingForm:FormGroup=new FormGroup({

    course_name: new FormControl("", Validators.required),
    total_marks: new FormControl("", Validators.required),
    // teacher_id : new FormControl("", Validators.required)


  })

  ngOnInit(): any {
    this.http.getTeachers().subscribe(data=>{
      this.teachers =data
      console.log(this.teachers)
    })
  }

  // addCourse(){
  //   // console.log(this.courseAddingForm.value);
  //   if (this.courseAddingForm.valid) {
  //     this.http.addCourse(this.courseAddingForm.value).subscribe(data => console.log(data))
  //   }
  //   else {
  //     console.log('fill properly ');
  //     this.formNotValid = true
  //     // console.log(this.courseAddingForm.valid);
  //   }
  //   console.log(this.courseAddingForm.value);

  // }
  addCourse(){
    console.log(this.courseAddingForm.value);
    this.http.AddCourse({ 'form': this.courseAddingForm.value, 'username': localStorage.getItem('username') }).subscribe(data=>{
      console.log(data);
    })
  }
}
