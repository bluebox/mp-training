import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Editor, Validators } from 'ngx-editor';
import { BlogService } from '../../services/blog.service';

@Component({
  selector: 'app-create-blog',
  templateUrl: './create-blog.component.html',
  styleUrls: ['./create-blog.component.css']
})
export class CreateBlogComponent implements OnInit {

  blogForm = this.fb.group({
    title: ['', Validators.required],
    discussion: ['', Validators.required],
    tag: ['', Validators.required]
  })

  constructor(public fb: FormBuilder, public service: BlogService, private http: HttpClient, private router: Router) { }

  submit() {
    let username = localStorage.getItem('username')
    let blogData: any = this.blogForm.value
    blogData['user_id'] = username
    console.log(blogData);
    this.service.addBlog(blogData).subscribe((data) => {
      console.log(data)
      alert("blog published successfully")
      this.router.navigate(['blogs'])
    })
  }
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


}
