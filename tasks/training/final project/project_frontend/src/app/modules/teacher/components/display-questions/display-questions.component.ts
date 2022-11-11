import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TeacherServiceService } from '../../teacher-service.service';

@Component({
  selector: 'app-display-questions',
  templateUrl: './display-questions.component.html',
  styleUrls: ['./display-questions.component.css']
})
export class DisplayQuestionsComponent implements OnInit {

  displayedColumns: string[]=['s.no','question_name','course', 'Action']
  questions: any
  i:any

  dataSource: MatTableDataSource<DisplayQuestionsComponent>;
  
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  text:any
  result:any
  

  constructor(private router:Router,   private http : TeacherServiceService) { }

 
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

  updateCourse(id : any){
    console.log('rakesh')
    this.router.navigate(['teacher/edit-question' , id])
  }
  add(){
    this.router.navigate(['teacher/questionregister'])
  }

  onSearchText(text: any,event) {
    this.text = event.target.value;
    console.log(text);
    this.http.getFque(text).subscribe({
      next:(res)=>{
        this.result= res;
        this.questions=res;
        console.log(this.result)
      }
    })
  }

  }