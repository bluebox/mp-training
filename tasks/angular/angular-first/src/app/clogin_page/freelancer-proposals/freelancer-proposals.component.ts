import { Component, OnInit } from '@angular/core';
import { clippingParents } from '@popperjs/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-freelancer-proposals',
  templateUrl: './freelancer-proposals.component.html',
  styleUrls: ['./freelancer-proposals.component.css']
})
export class FreelancerProposalsComponent implements OnInit {
  getJobProposals(job_id: any) {
    console.log(job_id);
    window.sessionStorage.setItem('job_id', job_id)
  }

  constructor(private service: ServiceService) { }
  jobsData: any;
  ngOnInit(): void {
    this.getClientIdUrl()
  }

  getClientIdUrl() {
    this.service.getJobsOfClientIdUrl(this.client_id_parse.client_id).subscribe((data: any) => this.jobsData = data)

  }
  client_id  : any = window.sessionStorage.getItem('cuser')
  client_id_parse = JSON.parse(this.client_id)

}
