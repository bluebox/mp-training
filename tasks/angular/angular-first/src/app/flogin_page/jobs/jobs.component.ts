import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {
  page = 0
  pageSize = 1
  
getJobId(job_id: any) {
  console.log(job_id);
  window.sessionStorage.setItem('job_id',job_id)
  // this.router.navigate(['./proposal_details']
  
}

  constructor(private service : ServiceService,private router : Router) { }
  jobsData : any ;
  ngOnInit(): void {
    this.getClientJobs()
  }

  getClientJobs(){
    this.service.getClientJobsUrl().subscribe((data : any ) => this.jobsData  = data)
  }
}
