import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-problems',
  templateUrl: './problems.component.html',
  styleUrls: ['./problems.component.css']
})
export class ProblemsComponent implements OnInit {

  problems!: any
  accuracies: any;
  sortBasedOnDifficulty: Number = 0;
  sortBasedOnAccuracy: Number = 0;

  constructor(public service: RegisterService, private http: HttpClient, private router: Router) {
    this.service.getProblems().subscribe((data: any) => {
      this.problems = data
      console.log(data)
    })
  }

  sort_based_on_diff() {
    if (this.sortBasedOnDifficulty == 0) {
      this.sortBasedOnDifficulty = 1;
    }
    else if(this.sortBasedOnDifficulty == 1) {
      this.sortBasedOnDifficulty = -1;
    }
    else{
      this.sortBasedOnDifficulty = 1;
    }
    this.sortProblems();
  }

  sort_based_on_acc() {
    if (this.sortBasedOnAccuracy == 0) {
      this.sortBasedOnAccuracy = 1;
    }
    else if(this.sortBasedOnAccuracy == 1) {
      this.sortBasedOnAccuracy = -1;
    }
    else{
      this.sortBasedOnAccuracy = 1;
    }
    this.sortProblems();
  }

  sortProblems() {
    let url = "http://127.0.0.1:8000/api/problems/sort"
    const params = new HttpParams()
      .set('difficulty', String(this.sortBasedOnDifficulty))
      .set('accuracy', String(this.sortBasedOnAccuracy));
    console.log(this.sortBasedOnAccuracy, this.sortBasedOnDifficulty);
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Authorization': 'token ' + localStorage.getItem('token')
      }
    )
    this.http.get(url, { headers: headers, params: params }).subscribe((data: any) => {
      console.log(typeof (data))
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
