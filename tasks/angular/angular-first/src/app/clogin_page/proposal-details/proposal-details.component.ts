import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-proposal-details',
  templateUrl: './proposal-details.component.html',
  styleUrls: ['./proposal-details.component.css']
})
export class ProposalDetailsComponent implements OnInit {

  proposalDetails: any;
  client: any = window.sessionStorage.getItem('cuser');
  job_id = window.sessionStorage.getItem('job_id');
  data: any = sessionStorage.getItem('job_id')
  contractDetails: any;
  
  constructor(private service: ServiceService, private fb: FormBuilder) { }
  createcontract: any = FormGroup
  updatefreelanceproposal : any = FormGroup
  ngOnInit(): void {
    this.service.getProposalDetails(JSON.parse(this.data)).subscribe(data => { this.proposalDetails = data; console.log(data);});

  }
  CreateContract(object: any) {
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
      'proprosal_status': 'accepted'
     });
    console.log(this.updatefreelanceproposal.value);
    this.service.updatefreelanceproposal(object.proprosal_id,this.updatefreelanceproposal.value).subscribe(data => { console.log(data); });
    this.service.createContract(this.createcontract.value).subscribe((data) => { this.contractDetails = data; console.log(this.contractDetails);  }, error => console.log(error));
  }



}
