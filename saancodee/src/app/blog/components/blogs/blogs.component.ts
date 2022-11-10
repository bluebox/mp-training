import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BlogService } from '../../services/blog.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-blogs',
  templateUrl: './blogs.component.html',
  styleUrls: ['./blogs.component.css']
})
export class BlogsComponent implements OnInit, OnDestroy {
  blogs: any;
  subscription!: Subscription

  constructor(public service: BlogService, private http: HttpClient, private router: Router) {
    // this.subscription = this.service.getBlogs().subscribe((data: any) => {
    //   this.blogs = data;
    //   console.log(data);
    // })
  }

  getBlog(blog_id: any) {
    this.router.navigate(['blogs/blog', blog_id]);
  }

  ngOnInit(): void {
    this.subscription = this.service.getBlogs().subscribe((data: any) => {
      this.blogs = data;
      console.log(data);
    })
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
