import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BlogService } from '../../services/blog.service';

@Component({
  selector: 'app-blog-category-search',
  templateUrl: './blog-category-search.component.html',
  styleUrls: ['./blog-category-search.component.css']
})
export class BlogCategorySearchComponent implements OnInit {

  searchWord: any;
  blogs: any = {};

  constructor(private router: Router, private route: ActivatedRoute, private service: BlogService) {
    this.route.queryParams
      .subscribe(params => {
        console.log(params);
        this.searchWord = params['tag'];
        console.log(this.searchWord);
        this.service.searchCategoryBlog(this.searchWord).subscribe((data: any) => {
          this.blogs = data;
          console.log(this.blogs);
        })
      }
    );
    
    this.service.searchCategoryBlog(this.searchWord).subscribe((data: any) => {
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
