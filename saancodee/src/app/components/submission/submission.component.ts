import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-submission',
  templateUrl: './submission.component.html',
  styleUrls: ['./submission.component.css']
})
export class SubmissionComponent {

  submission!:any;
  problem_id!:any;
  submissions!:any;
  data!:any;
  submissionsFlag!:any;
  submissionFlag: boolean = false;
  obs!:Subscription

  constructor(public service:RegisterService, private route:ActivatedRoute, private router:Router) { 
    this.problem_id = this.route.snapshot.params['id']
    console.log("loaded c");
    this.obs = this.service.getSubmissions(this.problem_id).subscribe((data) => {
      console.log(data);
      this.submissions = data;
   })
  }

  ngOnDestroy() {
    this.obs.unsubscribe();
  }
}

