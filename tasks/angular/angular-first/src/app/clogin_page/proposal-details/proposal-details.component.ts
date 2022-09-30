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
  ngOnInit(): void {
    this.service.getProposalDetails(JSON.parse(this.data)).subscribe(data => { this.proposalDetails = data; console.log(data);});

  }
  CreateContract(object: any) {
    this.createcontract = this.fb.group({
      'emp_proposal_id': object.proprosal_id,
      'client_id': JSON.parse(this.client).client_id,
      'contract_amount' : this.proposalDetails.requried_pay
    });
    console.log(this.createcontract.value);
    
    this.service.createContract(this.createcontract.value).subscribe((data) => { this.contractDetails = data; console.log(this.contractDetails); }, error => console.log(error));
  }



}
