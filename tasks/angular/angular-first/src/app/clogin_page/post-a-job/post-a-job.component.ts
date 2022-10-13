import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-post-a-job',
  templateUrl: './post-a-job.component.html',
  styleUrls: ['./post-a-job.component.css']
})
export class PostAJobComponent implements OnInit {

  receivedData: string = ''
  job_form!: FormGroup;
  constructor(private fb: FormBuilder, private service: ServiceService) { }
  data: any = sessionStorage.getItem('cuser');
  client_id: any = JSON.parse(this.data);
  ngOnInit(): void {
    this.job_form = this.fb.group({
      'client_id': this.client_id.client_id,
      'project_title': new FormControl('', Validators.required),
      'description': new FormControl('', Validators.required),
      'total_pay': new FormControl('', [Validators.required]),
      'experience_level': new FormControl('', [Validators.required]),
      'skills_requried': new FormControl('', [Validators.required]),

    })
  }
  registerSubmit() {
    this.service.postAJob(this.job_form.value).subscribe((data: any) => {
      console.log(data);
      alert('Successfully posted');
      location.reload();
    }, (error) => {
      alert("invalid details");
    }
    )
    console.log(this.job_form.value)
  }

}
