import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-edit-proposal',
  templateUrl: './edit-proposal.component.html',
  styleUrls: ['./edit-proposal.component.css']
})
export class EditProposalComponent implements OnInit {
submitSendProposal() {
  this.service.EditProposalUrl(this.proposal_id,this.send_proposal.value).subscribe(data => { console.log(data);alert('edited successfully');window.location.reload();});
  console.log(this.send_proposal.value);
}
  // send_proposal!: FormGroup;
  proposal_id: any;
  job_id: any;
  edit_proposal!: any;

    
  ngOnInit(): void {
    // console.log('gfff');
    
   
    // console.log(this.edit_proposal);
    
    
}

  constructor(private fb : FormBuilder,private arouter : ActivatedRoute,private service : ServiceService) {
    // console.log("construct");
    this.arouter.params.subscribe(params => {
      this.proposal_id=params['id']
      this.job_id=params['job_id']
      this.service.getProposal(this.proposal_id).subscribe(data => { 
        this.edit_proposal= data ;
        this.send_proposal.get('job_id')?.setValue(this.job_id)
        this.send_proposal.get('proposal_id')?.setValue(this.proposal_id)
        this.send_proposal.get('freelancer_id')?.setValue(this.parse_data.id)
        this.send_proposal.get('skills')?.setValue(this.edit_proposal.skills)
        this.send_proposal.get('cover_letter')?.setValue(this.edit_proposal.cover_letter)
        this.send_proposal.get('requried_pay')?.setValue(this.edit_proposal.requried_pay)

      //   this.send_proposal = this.fb.group({
      //     'job_id' : this.job_id,
      //     'proposal_id' : this.proposal_id,
      //     'freelancer_id' : this.parse_data.id,
      //     'skills': new FormControl(this.edit_proposal.skills, [Validators.required]),
      //     'cover_letter': new FormControl(this.edit_proposal.cover_letter, [Validators.required]),
      //     'requried_pay': new FormControl(this.edit_proposal.requried_pay, Validators.required)
      // });
      }, err => { console.log(err); });
      
    } );
    
   }
  
   send_proposal= new FormGroup({
    job_id : new FormControl( '', Validators.required),
    proposal_id : new FormControl( '', Validators.required),
    freelancer_id : new FormControl( '', Validators.required),
    skills : new FormControl( '', Validators.required),
    cover_letter : new FormControl( '', Validators.required),
    requried_pay : new FormControl( '', Validators.required),
   })

  data: any = localStorage.getItem('fuser')
  parse_data = JSON.parse(this.data)

}
