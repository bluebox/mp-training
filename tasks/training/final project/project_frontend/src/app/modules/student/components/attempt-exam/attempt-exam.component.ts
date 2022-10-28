import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-attempt-exam',
  templateUrl: './attempt-exam.component.html',
  styleUrls: ['./attempt-exam.component.css']
})
export class AttemptExamComponent implements OnInit {

  i:any
  flag:boolean = true
  exam_name:any
  exams:any


  constructor(private router:Router, private http: StudentServiceService,private actRouter:ActivatedRoute) { 
   
    this.actRouter.params.subscribe(data=>{
      console.log("subject",data)
      this.exam_name=(data['course_name'])
      console.log('rakesh',this.exam_name)
    })

  }

  ngOnInit(): void {

    this.http.getQuestionlength(this.exam_name).subscribe({
      next:(resp)=>{
        this.exams=resp
        console.log(this.exams)
        this.i=this.exams.length
      }
    
     })

  }
  takeexam(){
    this.router.navigate(['student/takeexam',this.exam_name])

  }
  showexam(){
    this.router.navigate(['student/showexam'])
  }

  
}
