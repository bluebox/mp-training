import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';
import { TakeExamComponent } from '../take-exam/take-exam.component';

@Component({
  selector: 'app-total-attempts',
  templateUrl: './total-attempts.component.html',
  styleUrls: ['./total-attempts.component.css']
})
export class TotalAttemptsComponent implements OnInit {

  i:any=0
  scores:any
  exam_name:any
  displayedColumns: string[]=['exam_name', 'score','date']



  constructor(private router:Router, private http:StudentServiceService, private actRouter:ActivatedRoute) {

    this.actRouter.params.subscribe(data=>{
      console.log(data)
      this.exam_name=(data['course_name'])
      console.log(this.exam_name)
    })

   }

  ngOnInit(): void {
    
    this.http.sendAttempts(this.exam_name).subscribe({
      next:(resp)=>{
        this.scores=resp
        console.log(this.scores)
      }
    
     })

  }

}
