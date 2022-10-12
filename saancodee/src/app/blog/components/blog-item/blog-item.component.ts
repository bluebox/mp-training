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
  editPopUpFlag: boolean = false;
  deletePopUpFlag: boolean = false;

  commentForm = this.fb.group ({
    comment: ['', [Validators.required]]
  })
  
  constructor(public fb:FormBuilder, public service: BlogService, private http: HttpClient, private router: Router, private route: ActivatedRoute) { 
    this.blog_id = this.route.snapshot.params['id'];
    console.log(this.blog_id);
    this.service.getBlogData(this.blog_id).subscribe((data) => {
      this.blog = data;
      console.log(data);
    })
   }

   edit_blog() {
    this.editPopUpFlag = !this.editPopUpFlag;
   }

   delete_blog() {
    this.deletePopUpFlag = !this.deletePopUpFlag
   }

   oncommentSubmit() {
    // this.service.addcomment(this.commentForm.value.comment, this.response.discussion_id).subscribe((data) => {
    //   console.log(data)
    //   location.reload();
    // })
   }

  ngOnInit(): void {
  }

}
