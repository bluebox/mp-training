import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {
  blogs: any;
  tagsCount: any = {};

  constructor(public service: RegisterService, private http: HttpClient, private router: Router) {
    this.service.getBlogs().subscribe((data: any) => {
      this.blogs = data
      console.log(data)
      for (let i of data) {
        let s = i['tag'];
        console.log(i['tag']);
        if (this.tagsCount[s]) {
          this.tagsCount[s] = this.tagsCount[s] + 1;
        }
        else {
          this.tagsCount[s] = 1;
        }
      }
      console.log(this.tagsCount);
    })
  }

  getBlog(blog_id:any) {
      this.router.navigate(['blog', blog_id]);
  }
  
  
  ngOnInit(): void {
  }

}