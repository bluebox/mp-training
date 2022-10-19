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
    // const wysi = document.querySelector(".bcontent")
    // if (wysi != null) {
    //   wysi.wysihtml5();
    // }
   }

  onSubmit() {
    this.service.postDiscussion(this.postDiscussionForm.value, this.id).subscribe((data:any) => {
      if (data['status'] == 200) {
        this.router.navigate(['problems/discussions/', this.id])
      }
    })
  }

  ngOnInit(): void {
  }

}
