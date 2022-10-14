import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BlogService } from '../../services/blog.service';

@Component({
  selector: 'app-blog-item',
  templateUrl: './blog-item.component.html',
  styleUrls: ['./blog-item.component.css']
})
export class BlogItemComponent implements OnInit {
  blog_id: any;
  blog: any;
  comments: any;
  editPopUpFlag: boolean = false;
  deletePopUpFlag: boolean = false;

  commentForm = this.fb.group ({
    comment: ['', [Validators.required]]
  })

  editDiscussionForm = this.fb.group({
    editedTitle: ['', Validators.required],
    editedDiscussion: ['', Validators.required]
  })
  
  constructor(public fb:FormBuilder, public service: BlogService, private http: HttpClient, private router: Router, private route: ActivatedRoute) { 
    this.blog_id = this.route.snapshot.params['id'];
    console.log(this.blog_id);
    this.service.getBlogData(this.blog_id).subscribe((data) => {
      this.blog = data;
      console.log(data);
    })
    this.service.getBlogComments(this.blog_id).subscribe((data: any) => {
      console.log("////////")
      console.log(data);
      this.comments = data
    })
   }

   checkCommentedUser(user_id:any) {
    if (localStorage.getItem('username') == user_id) {
      return true;
    }
    return false;
   }

   delete_comment(comment_id: any) {
    
   }

   oncommentSubmit() {
    this.service.addcomment(this.commentForm.value.comment, this.blog.blog_id).subscribe((data) => {
      console.log(data)
      location.reload();
    })
   }

   edit_blog() {
    this.editPopUpFlag = !this.editPopUpFlag;
   }

   delete_blog() {
    this.deletePopUpFlag = !this.deletePopUpFlag
   }

  //  oncommentSubmit() {
    // this.service.addcomment(this.commentForm.value.comment, this.response.discussion_id).subscribe((data) => {
    //   console.log(data)
    //   location.reload();
    // })
  //  }

   edit_confirm(blog_id:any) {
    let data = {"blog_id": blog_id, "title":this.editDiscussionForm.value.editedTitle, "discussion": this.editDiscussionForm.value.editedDiscussion}
    console.log(data)
    this.service.editDiscussion(data).subscribe((data:any) => {
      console.log(data)
      if (data.status == 200) {
        alert("updated succesfully")
        location.reload();
      }
    })
   }

   cancel_edit() {
    this.editPopUpFlag = !this.editPopUpFlag;
   }

   delete_confirm(blog_id:any) {
    let data = {"blog_id": blog_id}
    console.log(data)
    this.service.deleteDiscussion(data).subscribe((data:any) => {
      console.log(data)
      if (data.status == 200) {
        alert("deleted succesfully")
        history.back()
      }
    })
   }

   cancel_delete() {
    this.deletePopUpFlag = !this.deletePopUpFlag
   }

  ngOnInit(): void {
  }

}
