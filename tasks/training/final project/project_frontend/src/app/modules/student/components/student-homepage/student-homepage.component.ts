import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-student-homepage',
  templateUrl: './student-homepage.component.html',
  styleUrls: ['./student-homepage.component.css']
})
export class StudentHomepageComponent implements OnInit {
  courses:any
  questions:any
  i=0
  j=0

  constructor(private router:Router, private http:StudentServiceService) { }

  ngOnInit(): void {

    this.http.getCourses().subscribe({
      next:(resp)=>{
        this.courses=resp
       this.j=this.courses.length
        console.log(this.courses) 
      }

      
    })

    this.http.getQuestions().subscribe({
      next:(resp)=>{
        this.questions=resp
        this.i=this.questions.length
       
        console.log(this.questions.length) 
      }
      
    })

    // for (var v of this.courses){
    //   console.log(v)
    // }






    // for (var v of this.questions){
    //   this.j++;
    // }
    



    // this.i=this.courses.length
    // console.log(this.i)
    // this.j=this.questions.length
    // console.log(this.j)


  }

}
