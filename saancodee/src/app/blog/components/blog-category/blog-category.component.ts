import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BlogService } from '../../services/blog.service';

@Component({
  selector: 'app-blog-category',
  templateUrl: './blog-category.component.html',
  styleUrls: ['./blog-category.component.css']
})
export class BlogCategoryComponent implements OnInit {
  blogs: any;

  searchForm = this.fb.group(
    {
      search: ['', Validators.required]
    }
  )

  constructor(private fb: FormBuilder, public service: BlogService, private http: HttpClient, private router: Router) {
    this.service.getBlogs().subscribe((data: any) => {
      this.blogs = data;
      console.log(data);
    })
  }

  onSearch(search: any) {
    this.router.navigate(
      ['blogs/search'],
      {
        queryParams: { word: search }
      }
    )
  }

  ngOnInit(): void {
  }

}
