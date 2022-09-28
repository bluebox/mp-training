import { HttpBackend, HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { json } from 'express';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private router: Router, private http: HttpClient) { }

  getToken() {
    return localStorage.getItem('token')
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
    return this.http.get(url, {headers:headers})
  }

  getCategory(categoryId:any) {
    let url = `http://127.0.0.1:8000/api/problems/category/${categoryId}/`
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.get(url, {headers:headers})
  }

  editProfile(data:any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/edit-profile/${username}/`
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.post(url, data, {headers:headers})
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
    let url = "http://127.0.0.1:8000/api/problems"
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.get(url, {headers:headers})
  }

  postQuestion(data:any) {
    let username = localStorage.getItem('username')
    let url = `http://127.0.0.1:8000/api/post-question/${username}/`
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, {headers})
  }

  submitProblem(data:any) {
    console.log("running")
    let url = 'http://127.0.0.1:8000/api/submit-problem';
    let headers = new HttpHeaders({
      "Content-Type" : "application/json",
      'Authorization': 'token ' + localStorage.getItem('token')
    })
    return this.http.post(url, data, {headers:headers})
  }

  getProblem(id:any) {
    let url = `http://127.0.0.1:8000/api/problem-detail/${id}`
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    return this.http.get(url, {headers:headers})
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
