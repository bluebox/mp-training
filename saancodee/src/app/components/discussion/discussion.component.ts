import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';
import { FormBuilder, Validators } from '@angular/forms';
import { VirtualTimeScheduler } from 'rxjs';

@Component({
  selector: 'app-discussion',
  templateUrl: './discussion.component.html',
  styleUrls: ['./discussion.component.css']
})
export class DiscussionComponent implements OnInit {

  params!:any;
  data!:any;
  response!:any;
  comments!:any;
  commentFlag!:any;

  commentForm = this.fb.group ({
    comment: ['', [Validators.required]]
  })
  editCommentFlag: Boolean = false;
  editCommentValue: any;

  constructor(private fb:FormBuilder, private router: Router, private route: ActivatedRoute, public service:RegisterService) { 
    this.params = this.route.snapshot.params;
    this.data = {id:this.params['id'], discussionId: this.params['discussionId']}
    this.service.getDiscussion(this.data).subscribe((data:any) => {
      console.log(data)
      this.response = data['discussion'];
      this.comments = data['comments']
    })
   }

   edit_comment(comment_id:any, comment:any) {
    console.log(this.editCommentValue)
    this.service.edit_comment(comment_id, comment).subscribe((data) => {
      console.log(data)
    })
   }

   toggle_edit_comment_flag() {
    this.editCommentFlag = !this.editCommentFlag
   }

   addComment() {
    this.commentFlag = !this.commentFlag;
   }

   oncommentSubmit() {
    this.service.addcomment(this.commentForm.value.comment, this.response.discussion_id).subscribe((data) => {
      console.log(data)
      location.reload();
    })
   }

  ngOnInit(): void {
  }

}
