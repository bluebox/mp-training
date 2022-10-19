import { Component, OnInit } from '@angular/core';
import { clippingParents } from '@popperjs/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-freelancer-proposals',
  templateUrl: './freelancer-proposals.component.html',
  styleUrls: ['./freelancer-proposals.component.css']
})
export class FreelancerProposalsComponent implements OnInit {
edit(job_id: number , data : any ) {
  this.service.editJob(job_id , data ).subscribe((data: any) => { console.log(data); }, (err: any) => { console.log(err); });
  
}
delete(job_id: any) {
  if ( confirm('Are you sure you want to delete this ?') ) {
    this.service.deleteJob(job_id).subscribe( (data: any)  => { alert(data) ; console.log(data); location.reload() }, (err: any) => { alert(err) ; console.log(err);})
  }
}
  // getJobProposals(job_id: any) {
  //   console.log(job_id);
  // window.sessionStorage.setItem('job_id', job_id)
  // }

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
