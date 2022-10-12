import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';
import { Validators, FormBuilder } from '@angular/forms'
import { VirtualTimeScheduler } from 'rxjs';
declare var jQuery: any;

// const app = document.querySelector(".hints");

@Component({
  selector: 'app-problem',
  templateUrl: './problem.component.html',
  styleUrls: ['./problem.component.css']
})
export class ProblemComponent implements OnInit {

  panelOpenState = false;

  id!:any
  problem:any;
  tags:any;
  relatedTopicsFlag: boolean = false;
  hintsFlag: boolean = false;

  submissionForm:any = this.fb.group(
    {
      languages : ['', [Validators.required]],
      code : ['', [Validators.required]],
      stdin : ['']
    }
    )
  votes!: any;

  constructor(private router:Router, private fb:FormBuilder, public service: RegisterService, private route: ActivatedRoute, private http: HttpClient) { 
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
    console.log(this.submissionForm.value.code)
    this.submissionForm.value.stdin = this.problem.test_cases + "\n"
    console.log("running")
    this.service.submitProblem(this.submissionForm.value, this.id).subscribe((data:any) =>{ 
      console.log(data, this.problem.outputs, this.problem.test_cases)
      let res = {
        "status": 0,
        'solution': '',
        "result": '',

      }
      if (data['output'] == 'wrong answer') {
        let show = data['output'] + "\ninput: " + data['input'] + '\nexpected: ' + data['expected'] + "\nyours output: " + data['result']
        res['status'] = 2;
        res['result'] = show;
      }
      else {
        res['status'] = 1;
        res['result'] = "all test cases passed"
      }
      res['solution'] = this.submissionForm.value.code;
      // this.res = data;
      this.service.setSubmission(res);
      this.service.addToSubmissions(res, this.id).subscribe((data) => {
        // console.log(data);
      })
      this.router.navigate(['submissions', this.id])
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
    (function ($) {
      $(document).ready(function(){
        console.log("Hello from jQuery!");
      });
    })(jQuery);
  }

}
