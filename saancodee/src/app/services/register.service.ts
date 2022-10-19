import { HttpBackend, HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { json } from 'express';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  submission: BehaviorSubject<any>;
  profileData!: {};
  discussionData: any;

  constructor(private router: Router, public route: ActivatedRoute, private http: HttpClient) {
    this.submission = <BehaviorSubject<any>>new BehaviorSubject(null);
  }

  getToken() {
    return localStorage.getItem('token')
  }

  set discussion(data: any) {
    this.discussionData = data;
  }

  get discussion() {
    return this.discussionData;
  }

  logOut() {
    localStorage.removeItem('token');
    this.router.navigate(['login'])
  }

  getProfile() {
    let username = localStorage.getItem('username')
    let url = "http://127.0.0.1:8000/api/profile/" + username
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.get(url, { headers: headers })
  }

  getCategory(categoryId: any) {
    let url = `http://127.0.0.1:8000/api/problems/category/${categoryId}/`
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.get(url, { headers: headers })
  }

  editDiscussionComment(data: any) {
    let url = "http://127.0.0.1:8000/api/edit-discussion-comment"
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.post(url, data, { headers: headers })
  }

  editProfile(data: any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/edit-profile/${username}/`
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.post(url, data, { headers: headers })
  }

  // sortProblems(problems) {
  //   let url = "http://127.0.0.1:8000/api/problems/sort"
  //   const headers = new HttpHeaders(
  //     {
  //       'Content-Type': 'application/json',
  //       'Authorization': 'token ' + localStorage.getItem('token')
  //     }
  //   )
  //   return this.http.get(url, {headers:headers})
  // }

  getProblems() {
    // let param = new HttpParams();
    // this.route.queryParams
    //   .subscribe(params => {
    //     console.log(params);

    //     param.append('level', params['level']);
    //   })
    let url = "http://127.0.0.1:8000/api/problems"
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )

    return this.http.get(url, { headers: headers })
  }

  addcomment(comment: any, discussionId: any) {
    let username = localStorage.getItem('username')
    let data = {
      "user_id": username,
      "discussion_id": discussionId,
      "comment": comment
    }
    let url = `http://127.0.0.1:8000/api/comment/post/${discussionId}/${username}`
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.post(url, data, { headers: headers })
  }

  getStats(username: any) {
    let data = { "username": username }
    let url = "http://127.0.0.1:8000/api/problems-statistics"
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers })
  }

  postQuestion(data: any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/post-question/${username}/`
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers })
  }

  setSubmission(data: any) {
    this.submission.next(data);
  }

  set profile(data: any) {
    this.profileData = data;
  }

  get profile() {
    return this.profileData;
  }

  addToSubmissions(data: any, id: any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/addSubmission/${id}/${username}`;
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers: headers })
  }

  getSubmissions(id: any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/submissions/${id}/${username}`;
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.get(url, { headers: headers })
  }

  postDiscussion(data: any, problem_id: any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/post-discussion/${problem_id}/${username}`;
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers: headers })
  }

  getSubmission() {
    return this.submission.asObservable();
  }

  submitProblem(data: any, id: any) {
    console.log("running")
    let url = `http://127.0.0.1:8000/api/submit-problem/${id}`;
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers: headers })
  }

  getVote(id: any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/votes/${id}/${username}`
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.get(url, { headers: headers })
  }

  edit_comment(comment_id: any, comment: any) {
    let url = "http://127.0.0.1:8000/api/edit-comment"
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, { 'comment_id': comment_id, 'comment': comment }, { headers: headers })
  }

  deleteDiscussion(data: any) {
    console.log(data);
    let url = "http://127.0.0.1:8000/api/delete-discussion"
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers: headers })
  }

  deleteComment(id: any) {
    console.log();
    let url = "http://127.0.0.1:8000/api/delete-comment"
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, { "comment_id": id }, { headers: headers })
  }

  editDiscussion(data: any) {
    console.log(data);
    let url = "http://127.0.0.1:8000/api/edit-discussion"
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers: headers })
  }

  postVote(id: any, vote: any) {
    let data = { "vote": vote }
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/votes/${id}/${username}`
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, { headers: headers })
  }

  getDiscussion(data: any) {
    let url = `http://127.0.0.1:8000/api/discussion/${data.id}/${data.discussionId}`
    let headers = new HttpHeaders({
      "Content-Type": "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.get(url, { headers: headers, params: data })
  }

  getProblem(id: any) {
    let url = `http://127.0.0.1:8000/api/problem-detail/${id}`
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.get(url, { headers: headers })
  }

  doLogin() {
    if (localStorage.getItem('token')) {
      return true
    }
    else {
      return false
    }
  }

}
