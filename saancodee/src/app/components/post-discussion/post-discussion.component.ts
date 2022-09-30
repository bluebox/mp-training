import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-post-discussion',
  templateUrl: './post-discussion.component.html',
  styleUrls: ['./post-discussion.component.css']
})
export class PostDiscussionComponent implements OnInit {

  postDiscussionForm = this.fb.group(
    {
      title: ['', Validators.required],
      discussion: ['', Validators.required]
    }
  )

  id!:any;

  constructor(public fb:FormBuilder, private service : RegisterService, private router: Router, private route: ActivatedRoute) { 
    this.id = this.route.snapshot.params['id']
   }

  onSubmit() {
    this.service.postDiscussion(this.postDiscussionForm.value, this.id).subscribe((data) => {
      console.log(data)
    })
  }

  ngOnInit(): void {
  }

}
