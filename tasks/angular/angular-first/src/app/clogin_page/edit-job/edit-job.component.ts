import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-edit-job',
  templateUrl: './edit-job.component.html',
  styleUrls: ['./edit-job.component.css']
})
export class EditJobComponent implements OnInit {
  edit_proposal: any ;
registerSubmit() {
  console.log(this.job_form.value.is_deleted);
  
  this.service.editClientJob(this.job_id,this.job_form.value).subscribe(data => { this.edit_proposal = data;alert('edited successfully') ;location.reload() }, err => { console.log(err); });
  
}
  // job_form! : FormGroup;
  job_id : any;
  constructor(private fb : FormBuilder ,private arouter : ActivatedRoute ,private service : ServiceService ,private router : Router ) { }
  data: any = sessionStorage.getItem('cuser');
  client_id: any = JSON.parse(this.data);
  ngOnInit(): void {
    
    this.arouter.params.subscribe(params => {
      this.job_id=params['id'];
      this.service.getJobofClient(this.job_id).subscribe(data => { 
        this.edit_proposal= data ;
        console.log(this.edit_proposal.experience_level);
        
        // this.job_form.get('job_id')?.setValue(this.job_id)
        
        this.job_form.get('project_title')?.setValue(this.edit_proposal.project_title)
        this.job_form.get('experience_level')?.setValue(this.edit_proposal.experience_level)
        this.job_form.get('skills_requried')?.setValue(this.edit_proposal.skills_requried)
        this.job_form.get('description')?.setValue(this.edit_proposal.description)
        this.job_form.get('total_pay')?.setValue(this.edit_proposal.total_pay)
        this.job_form.get('client_id')?.setValue(this.client_id.client_id)
        this.job_form.get('job_id')?.setValue(this.job_id)
        this.job_form.get('is_deleted')?.setValue(this.edit_proposal.is_deleted)
        console.log(this.job_form.get('is_deleted'));
        

      //   this.send_proposal = this.fb.group({
      //     'job_id' : this.job_id,
      //     'proposal_id' : this.proposal_id,
      //     'freelancer_id' : this.parse_data.id,
      //     'skills': new FormControl(this.edit_proposal.skills, [Validators.required]),
      //     'cover_letter': new FormControl(this.edit_proposal.cover_letter, [Validators.required]),
      //     'requried_pay': new FormControl(this.edit_proposal.requried_pay, Validators.required)
      // });
      }, err => { console.log(err); });
      
    
    });



    // this.job_form = this.fb.group({
    //   'client_id': this.client_id.client_id,
    //   'project_title': new FormControl('', Validators.required),
    //   'description': new FormControl('', Validators.required),
    //   'total_pay': new FormControl('', [Validators.required]),
    //   'experience_level': new FormControl('', [Validators.required]),
    //   'skills_requried': new FormControl('', [Validators.required]),

    // })
  }

  job_form = new FormGroup({
    "client_id" : new FormControl('', [Validators.required]),
    "job_id" : new FormControl('', [Validators.required]),
    'project_title': new FormControl('', Validators.required),
    'description': new FormControl('', Validators.required),
    'total_pay': new FormControl('', [Validators.required]),
    'experience_level': new FormControl('', [Validators.required]),
    'skills_requried': new FormControl('', [Validators.required]),
    'is_deleted' : new FormControl(false,[Validators.required ])

})
deleteJob(){
      this.job_form.get('is_deleted')?.setValue(true)
      this.registerSubmit();
      this.router.navigate(['/client_login_page/freelancer_proposals']);
}
}
