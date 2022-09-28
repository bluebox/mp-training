import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';
import { Validators, FormBuilder } from '@angular/forms'

// const app = document.querySelector(".hints");

@Component({
  selector: 'app-problem',
  templateUrl: './problem.component.html',
  styleUrls: ['./problem.component.css']
})
export class ProblemComponent implements OnInit {

  id!:any
  problem:any;
  tags:any;
  relatedTopicsFlag: boolean = false;
  hintsFlag: boolean = false;

  submissionForm = this.fb.group(
    {
      languages : ['', [Validators.required]],
      code : ['', [Validators.required]],
      stdin : ['']
    }
    )
  votes!: any;

  constructor(private fb:FormBuilder, public service: RegisterService, private route: ActivatedRoute, private http: HttpClient) { 
    this.id = route.snapshot.params['id']
    
    this.service.getProblem(this.id).subscribe((data:any) => {
      this.problem = data.problems
      this.tags = data.tags
      console.log(data)
    })

    this.service.getVote(this.id).subscribe((data) => {
      this.votes = data;
      console.log(data);
    })

   }

   onSubmit() {
    console.log(this.problem.test_cases)
    this.submissionForm.value.stdin = this.problem.test_cases + "\n"
    console.log("running")
    this.service.submitProblem(this.submissionForm.value).subscribe((data) =>{
      console.log(data)
    })
   }

   postVote(vote:any) {
    this.service.postVote(this.id, vote).subscribe((data) => {
      this.votes = data;
      console.log(data);
    })
   }

   loadRelatedTopics() {
     this.relatedTopicsFlag = !this.relatedTopicsFlag;
     console.log(this.relatedTopicsFlag);
    }
    
    loadHints() {
    const app = document.querySelector(".hints") as HTMLDivElement;
    console.log(this.problem.hints)
    this.hintsFlag = !this.hintsFlag;
    console.log(this.hintsFlag);
   }

  ngOnInit(): void {
  }

}
