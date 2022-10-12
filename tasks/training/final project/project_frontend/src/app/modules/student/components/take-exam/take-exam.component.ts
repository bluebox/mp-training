import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-take-exam',
  templateUrl: './take-exam.component.html',
  styleUrls: ['./take-exam.component.css']
})
export class TakeExamComponent implements OnInit {
  displayedColumns:string[]=['question_name']
  g:any
  h:any
  total_questions:any
  arr:string[]=[]
  i:number=0
  score:number=0
  answers_user: string[] = new Array(10);
  answer_main:string[]=new Array(10)
  event:any
  k:any
  j:string='o'

  constructor(private router:Router,  private http:StudentServiceService) { }

  ngOnInit(): void {
    this.g=localStorage.getItem('question')
    this.h=JSON.parse(this.g)
    console.log(this.h)
  
    for(this.i=0;this.i<this.h.length;this.i++){
      this.k=this.h[this.i]      
      this.answer_main[this.i]=this.k.answer
    }
    console.log(this.answer_main)
    
  }

  onSubmit(){
    
    for(this.i=0;this.i<this.h.length;this.i++){
      if(this.answers_user[this.i]==this.answer_main[this.i]){
        this.score++;

      }
    }   
    console.log(this.score)
    localStorage.setItem("score",JSON.stringify(this.score));
    this.router.navigate(['student/showexam']);
 }


 onChange(i:number, event){

  // console.log(event.target.value)

  this.answers_user[i] = event.target.value;
  console.log(this.answers_user)
}


}
