import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { TeacherServiceService } from '../../teacher-service.service';

@Component({
  selector: 'app-display-questions',
  templateUrl: './display-questions.component.html',
  styleUrls: ['./display-questions.component.css']
})
export class DisplayQuestionsComponent implements OnInit {

  displayedColumns: string[]=['question_name','course', 'Action']
  questions: any
  
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  

  constructor(private http : TeacherServiceService) { }

  ngOnInit(): void {
    this.http.getRequiredQuestions().subscribe({
      next:(resp)=>{
        this.questions=resp
        console.log(this.questions)
      }
    })
     
  }

  deleteQuestion(arg: any) {

  if(confirm("do u want to delete")){ 
      var val = {"id": arg}
      console.log(val)
      this.http.deleteQuestions(val).subscribe(res=>{
        alert(res.toString())
        console.log(val)
        window.location.reload();
      })
    }
   }
  }