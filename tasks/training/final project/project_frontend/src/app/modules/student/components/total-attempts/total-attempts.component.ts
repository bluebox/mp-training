import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';
import { TakeExamComponent } from '../take-exam/take-exam.component';

@Component({
  selector: 'app-total-attempts',
  templateUrl: './total-attempts.component.html',
  styleUrls: ['./total-attempts.component.css']
})
export class TotalAttemptsComponent implements OnInit {
  // @ViewChild('takeexamcomponent', {static : false}) filterPanel: TakeExamComponent;
  // total_scores:number[]=[]
  i:any



  constructor(private router:Router, private auth:StudentServiceService) { }

  ngOnInit(): void {

    
    // console.log(this.auth.total_scores)

  }

}
