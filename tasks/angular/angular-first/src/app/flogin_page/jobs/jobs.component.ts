import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {
searchJob(search : any) {
  this.service.getClientJobsSearchUrl(search).subscribe((data: any) => this.jobsData = data)
}
first = 0;
rows = 5;
next() {
  this.first = this.first + this.rows;
}
prev() {
  this.first = this.first - this.rows;
}
reset() {
  this.first = 0;
}
isLastPage(): boolean {
  return this.jobsData ? this.first === (this.jobsData.length - this.rows) : true;
}
isFirstPage(): boolean {
  return this.jobsData ? this.first === 0 : true;
}

  getJobId(job_id: any) {
    console.log(job_id);

    // this.router.navigate(['./proposal_details']
    
    this.service.proposalExists(job_id,this.fuser_parse.id).subscribe((data: any) => {
      console.log(data.freelancer_id);
      
      if (data.freelancer_id==null) {
        this.router.navigate(['./freelance_login_page/jobs/send_proposal_page/' + job_id + '/'])
      }
      else {
        alert('proposal already  submitted'); this.router.navigate(['./freelance_login_page/jobs'])
        
      }
    });
  }

  constructor(private service: ServiceService, private router: Router) { }
  jobsData: any;
  ngOnInit(): void {
    this.getClientJobs()
    // console.log('ngoninit');
    
  }

  getClientJobs() {
    this.service.getClientJobsUrl().subscribe((data: any) => this.jobsData = data)
  }
  fuser : any = window.localStorage.getItem('fuser');
  fuser_parse : any = JSON.parse(this.fuser);
}
