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

  editDiscussion(data:any) {
    console.log(data);
    let url = "http://127.0.0.1:8000/api/edit-blog-discussion"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, {headers: headers})
  }

  addBlog(blogData: any) {
    let url = "http://127.0.0.1:8000/api/add-blog"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, blogData, {headers: headers})
  }

  editBlogComment(commentData: any) {
    let url = "http://127.0.0.1:8000/api/edit-blog-comment"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, commentData, {headers: headers})
  }

  submitReply(replyData: any) {
    let url = "http://127.0.0.1:8000/api/add-blog-reply"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, replyData, {headers: headers})
  }

  deleteBlogComment(commentData: any) {
    let url = "http://127.0.0.1:8000/api/edit-blog-comment"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.delete(url, {headers: headers, body: commentData})
  }

  deleteBlogCommentReply(replyData: any) {
    let url = "http://127.0.0.1:8000/api/delete-blog-comment-reply"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, replyData, {headers: headers})
  }

  getBlogComments(blog_id:any) {
    let data = {"blog_id": blog_id}
    console.log(data);
    let url = "http://127.0.0.1:8000/api/get-blog-comments"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, {headers: headers})
  }

  searchBlog(data: any) {
    let formdata = {
      "search": data
    }
    let url = "http://127.0.0.1:8000/api/blog-search"
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.post(url, formdata, {headers:headers})
  }

  searchCategoryBlog(data: any) {
    let formdata = {
      "tag": data
    }
    let url = "http://127.0.0.1:8000/api/blog-category"
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.post(url, formdata, {headers:headers})
  }

  addcomment(comment:any, blog_id:any) {
    let username = localStorage.getItem('username')
    let data = {
      "user_id": username,
      "blog_id": blog_id,
      "comment": comment,
    }
    let url = "http://127.0.0.1:8000/api/blog-comment"
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.post(url, data, {headers:headers})
  }

  deleteDiscussion(data:any) {
    console.log(data);
    let url = "http://127.0.0.1:8000/api/delete-blog-discussion"
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, {headers: headers})
  }

}
