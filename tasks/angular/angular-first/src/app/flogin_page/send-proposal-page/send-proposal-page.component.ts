import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-send-proposal-page',
  templateUrl: './send-proposal-page.component.html',
  styleUrls: ['./send-proposal-page.component.css']
})
export class SendProposalPageComponent implements OnInit {
  job_id! : any;
  data_job_id : any;
  send_proposal!: FormGroup;
  
  constructor(private service: ServiceService, private fb: FormBuilder,private arouter : ActivatedRoute,private router : Router) { }
  ngOnInit(): void {
    this.arouter.params.subscribe(params => this.job_id=params['job_id'] );
    // console.log(this.job_id);
    // this.data_job_id = window.sessionStorage.getItem('job_id')
    // this.job_id = JSON.parse(this.data_job_id);
    this.send_proposal = this.fb.group({
      'job_id' : this.job_id,
      'freelancer_id' : this.parse_data.id,
      'skills': new FormControl('', [Validators.required]),
      'cover_letter': new FormControl('', [Validators.required]),
      'requried_pay': new FormControl('', Validators.required)
    })

  }


  submitSendProposal() {
    this.service.submitSendProposalUrl(this.send_proposal.value).subscribe(data => { console.log(data);alert('successfully applied');this.router.navigate(['/freelance_login_page/proposals'])});
    console.log(this.send_proposal.value);

    
  }
  

  data : any = localStorage.getItem('fuser')
  parse_data = JSON.parse(this.data)

  get skills(){
    return this.send_proposal.get('skills')
  }
  get cover_letter(){
    return this.send_proposal.get('cover_letter')
  }

  get required_pay(){
    return this.send_proposal.get('required_pay');
  }
}
