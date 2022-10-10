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

  editDiscussionForm = this.fb.group({
    editedTitle: ['', Validators.required],
    editedDiscussion: ['', Validators.required]
  })

  editPopUpFlag: Boolean = false;
  editedTitle: any = '';
  editedDiscussion:any = '';
  deletePopUpFlag: Boolean = false;

  constructor(private fb:FormBuilder, private router: Router, private route: ActivatedRoute, public service:RegisterService) { 
    this.params = this.route.snapshot.params;
    this.data = {id:this.params['id'], discussionId: this.params['discussionId']}
    this.service.getDiscussion(this.data).subscribe((data:any) => {
      console.log(data)
      this.response = data['discussion'];
      this.comments = data['comments'];
    })
   }

   delete_discussion() {
    this.deletePopUpFlag = !this.deletePopUpFlag
   }

   cancel_delete() {
    this.deletePopUpFlag = !this.deletePopUpFlag
   }

   delete_confirm(discussion_id:any) {
    let data = {"discussion_id": discussion_id}
    console.log(data)
    this.service.deleteDiscussion(data).subscribe((data:any) => {
      console.log(data)
      if (data.status == 200) {
        alert("deleted succesfully")
        history.back()
      }
    })
   }

   edit_discussion() {
    this.editPopUpFlag = !this.editPopUpFlag;
   }

   checkCommentedUser(user_id:any) {
    if (localStorage.getItem('username') == user_id) {
      return true;
    }
    return false;
   }

   delete_comment(id:any) {
    this.service.deleteComment(id).subscribe((data) => {
      console.log(data)
    })
    alert("comment deleted successfully");
    location.reload();
   }

   cancel_edit() {
    this.editPopUpFlag = !this.editPopUpFlag;
   }

   edit_confirm(discussion_id:any) {
    let data = {"discussion_id": discussion_id, "title":this.editDiscussionForm.value.editedTitle, "discussion": this.editDiscussionForm.value.editedDiscussion}
    console.log(data)
    this.service.editDiscussion(data).subscribe((data:any) => {
      console.log(data)
      if (data.status == 200) {
        alert("updated succesfully")
        location.reload();
      }
    })
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
