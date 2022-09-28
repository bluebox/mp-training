import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-problems',
  templateUrl: './problems.component.html',
  styleUrls: ['./problems.component.css']
})
export class ProblemsComponent implements OnInit {

  problems!:any

  constructor(public service: RegisterService, private http: HttpClient, private router: Router) { 
    this.service.getProblems().subscribe((data) => {
      this.problems = data
      console.log(data)
    })
   }

   sortProblems() {
    let url = "http://127.0.0.1:8000/api/problems/sort"
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    this.http.get(url, {headers:headers}).subscribe((data) => {
      this.problems = data
    })
  }

  getProblem(problemId: any) {
    this.router.navigate(['problems/', problemId])
    // let url = "http://127.0.0.1:8000/api/problem-detail/" + String(problemId)
    // const headers = new HttpHeaders(
    //   {
    //     'Content-Type': 'application/json',
    //     'Authorization': 'token ' + localStorage.getItem('token')
    //   }
    // )
    // this.http.get(url, {headers:headers}).subscribe((data) => {
    //   console.log('====================================');
    //   console.log(data);
    //   this.router.navigate(['problems/:id', data])
    //   console.log('====================================');
    // })
  }

  ngOnInit(): void {
  }

}
