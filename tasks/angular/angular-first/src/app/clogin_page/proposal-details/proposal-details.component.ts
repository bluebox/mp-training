import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgbDayTemplateData } from '@ng-bootstrap/ng-bootstrap/datepicker/datepicker-view-model';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-proposal-details',
  templateUrl: './proposal-details.component.html',
  styleUrls: ['./proposal-details.component.css']
})
export class ProposalDetailsComponent implements OnInit {
rejectProposal(object : any) {
  this.proposal_status = 'rejected';
  this.CreateContract(object);
}

  proposalDetails: any;
  client: any = window.sessionStorage.getItem('cuser');
  // job_id = window.sessionStorage.getItem('job_id');
  // data: any = sessionStorage.getItem('job_id')
  data : any ;
  contractDetails: any;
  
  constructor(private service: ServiceService, private fb: FormBuilder,private route : ActivatedRoute ) { }
  createcontract: any = FormGroup
  updatefreelanceproposal : any = FormGroup
  proposal_status : any;
  ngOnInit(): void {
    this.route.params.subscribe(data => { this.data = data['id'];});
    this.service.getProposalDetails(this.data).subscribe(data => {
      this.proposalDetails = data; console.log(data);
      this.proposalDetails = this.proposalDetails.filter( (data : any) => {
    
        if (data.proprosal_status != 'rejected')
        {  
        
          return data 
        }
        
      })
    });

  }
  CreateContract(object: any) {
    if ( !this.proposal_status ){
      this.proposal_status= 'accepted';
    }
    console.log(this.proposal_status);
    
    this.createcontract = this.fb.group({
      'emp_proposal_id': object.proprosal_id,
      'client_id': JSON.parse(this.client).client_id,
      'contract_amount' : object.requried_pay
    });
    this.updatefreelanceproposal = this.fb.group({
      'proprosal_id': object.proprosal_id,
      'freelancer_id': object.freelancer_id,
      'requried_pay': object.requried_pay,
      'skills': object.skills,
      'job_id': object.job_id,
      'cover_letter': object.cover_letter,
      'proprosal_status': this.proposal_status
     });
    console.log(this.updatefreelanceproposal.value.proprosal_status);
    this.service.updatefreelanceproposal(object.proprosal_id,this.updatefreelanceproposal.value).subscribe(data => { console.log(data); });

    if (this.updatefreelanceproposal.value.proprosal_status === "accepted" ){
      this.service.createContract(this.createcontract.value).subscribe((data) => { this.contractDetails = data; console.log(this.contractDetails);  }, error => console.log(error));

    }
    location.reload();

  }



}
