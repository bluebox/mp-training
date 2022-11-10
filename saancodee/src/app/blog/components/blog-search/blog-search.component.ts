import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BlogService } from '../../services/blog.service';

@Component({
  selector: 'app-blog-search',
  templateUrl: './blog-search.component.html',
  styleUrls: ['./blog-search.component.css']
})
export class BlogSearchComponent implements OnInit {

  searchWord: any;
  blogs: any = {};

  constructor(private router: Router, private route: ActivatedRoute, private service: BlogService) {
    this.route.queryParams
      .subscribe(params => {
        console.log(params);
        this.searchWord = params['word'];
        console.log(this.searchWord);
        this.service.searchBlog(this.searchWord).subscribe((data: any) => {
          this.blogs = data;
          console.log(this.blogs);
        })
      }
    );
    
    this.service.searchBlog(this.searchWord).subscribe((data: any) => {
      this.blogs = data;
      console.log(this.blogs);
    })
   }

   getBlog(blog_id: any) {
    this.router.navigate(['blogs/blog', blog_id]);
  }

  ngOnInit() {
    // this.route.queryParams
    //   .subscribe(params => {
    //     console.log(params);
    //     this.searchWord = params['word'];
    //     console.log(this.searchWord);
    //   }
    // );
  }

}
