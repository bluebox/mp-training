import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';
import { MatDialog } from '@angular/material/dialog';
import { CloseComponent } from '../close/close.component';

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
  answers_user: string[] = [];
  answer_main:string[]=[]
  // total_scores:number[]=new Array(10)
  event:any
  k:any
  v:any
  j:string='o'
  emp:number=0;
  course_name:any

  constructor(private router:Router, private http:StudentServiceService, public actRouter:ActivatedRoute, public dialog: MatDialog) { 

    this.actRouter.params.subscribe(data=>{
      console.log("subject",data)
      this.course_name=(data['exam_name'])
      console.log('rakesh',this.course_name)
    })

  }

  ngOnInit(): void {
    this.http.getQuestionlength(this.course_name).subscribe({
      next:(resp)=>{
        this.h=resp
        console.log(this.h)
     
  
    for(this.i=0;this.i<this.h?.length;this.i++){
      this.k=this.h[this.i]  
    
      this.answer_main[this.i]=this.k.answer
    }
    console.log('ggg',this.answer_main)
    localStorage.setItem('length', JSON.stringify(this.h?.length))
    this.v=localStorage.getItem('course_name')

      }
    
    })
    
  }


 onChange(i:number, event){

  this.answers_user[i] = event.target.value;
  console.log(this.answers_user)
}

openDialog() {
  this.score =0
  this.emp=0

  for (var i of this.answers_user){
    if(!(i)){
      this.emp++;
      console.log(this.emp)
    }

  }

  for(this.i=0;this.i<this.h.length;this.i++){
    if(this.answers_user[this.i]==this.answer_main[this.i]){
      this.score++;
    }
  }   
 
  console.log(this.score)
  console.log(this.answer_main.length)
  console.log(this.answers_user.length)
  this.http.total_scores.push(this.score)
  
if((this.answers_user.length != this.answer_main.length)|| ((this.emp))) {

    let dialogRef = this.dialog.open(CloseComponent)
  
    dialogRef.afterClosed().subscribe(result => {

    if (result == 'true') {
      localStorage.setItem("score",JSON.stringify(this.score));
      this.http.AddScore({ 'score':this.score, 'exam_name':this.course_name }).subscribe(data=>{
        console.log(data);
      })
      this.router.navigate(['student/viewresult']);
    }
    })
  }

else{
  localStorage.setItem("score",JSON.stringify(this.score));
  this.http.AddScore({ 'score':this.score, 'exam_name':this.course_name }).subscribe(data=>{
    console.log(data);
  })
  this.router.navigate(['student/viewresult',this.course_name,this.score]);
}




}


 

}
