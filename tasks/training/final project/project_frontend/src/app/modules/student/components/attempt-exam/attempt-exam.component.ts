import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-attempt-exam',
  templateUrl: './attempt-exam.component.html',
  styleUrls: ['./attempt-exam.component.css']
})
export class AttemptExamComponent implements OnInit {

  i:any
  j:any
  k:any
  g:any
  h:any
  l:any
  flag:boolean = true
  constructor(private router:Router, private http: StudentServiceService) { 
   
  
  }

  ngOnInit(): void {
    

    this.i=localStorage.getItem('course_name')
    this.l=localStorage.getItem('username')
    this.j=localStorage.getItem('total_marks')
    this.k=localStorage.getItem('course_id')
    
    // console.log(localStorage.getItem('question'))
    this.g=localStorage.getItem('question')
    this.h=JSON.parse(this.g)


    console.log(this.h.length)


    
    // this.http.getQuestionWdCourse().subscribe({
    //   next:(resp)=>{
    //     this.questions=resp
       
    //     console.log(this.questions) 
    //     localStorage.setItem('questions',JSON.stringify(this.questions))
    //     console.log(localStorage.getItem('questions'))
    //   }
      
      
    // })
    // if (!(this.questions)){
    //   this.router.navigate(['/student/showexam'])
    //   console.log("venky")
      

    // }
   

  }

  
}
