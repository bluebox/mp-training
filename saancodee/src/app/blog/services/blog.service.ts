import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  allBlogs: any;

  constructor(private router: Router, private http: HttpClient) { }

  getBlogs() {
    let url = "http://127.0.0.1:8000/api/blogs"
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.get(url, { headers: headers })
  }

  // storeBlogs(data:any) {
  //   this.allBlogs = data;
  // }

  // getBlog(blog_id: any) {
  //   console.log(this.allBlogs)
  //   this.allBlogs.array.forEach((blog: any) => {
  //     if (blog['blog_id'] == blog_id) {
  //       return blog;
  //     }
  //   });
  // }

  getBlogData(id: any) {
    let url = "http://127.0.0.1:8000/api/blog"
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    let data = {
      "id": id
    }
    return this.http.post(url, data, { headers: headers })
  }
}
