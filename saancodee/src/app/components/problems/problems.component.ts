import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Validators } from 'ngx-editor';
import { RegisterService } from 'src/app/services/register.service';


@Component({
  selector: 'app-problems',
  templateUrl: './problems.component.html',
  styleUrls: ['./problems.component.css']
})
export class ProblemsComponent implements OnInit {

  allProblems!: any;
  problems!: any
  accuracies: any;
  sortBasedOnDifficulty: Number = 0;
  sortBasedOnAccuracy: Number = 0;
  searchWord = '';

  filterForm = this.fb.group({
    diff: ['', Validators.required]
  })

  constructor(public route: ActivatedRoute, public fb: FormBuilder, public service: RegisterService, private http: HttpClient, private router: Router) {
    this.service.getProblems().subscribe((data: any) => {
      this.problems = data
      this.allProblems = data
      console.log(data)
    })
  }

  submitSearch() {
    console.log(this.searchWord);
    let word: any = this.searchWord.trim()
    console.log(word);
    let keywords = word.split(" ")
    console.log(keywords);
    let problems = []
    for (let i = 0; i < this.allProblems.length; i ++) {
      for (let j = 0; j < keywords.length; j ++) {
        if (this.allProblems[i].problem_name.includes(keywords[j])) {
          problems.push(this.allProblems[i])
          break
        }
      }
    }
    this.problems = problems
  }

  submit() {

    const diffs = document.getElementById("group") as HTMLDivElement
    let diff = -1
    for (let i = 0; i < diffs.children.length; i ++) {
      const inp = diffs.children[i] as HTMLInputElement
      if (inp.checked) {
        diff = Number(inp.value)
      }
    }
    
    
    console.log(diff);
    let problems = []
    if (diff != -1) {
    for (let i = 0; i < this.allProblems.length; i ++) {
      if (this.allProblems[i].difficulty_level == diff) {
        problems.push(this.allProblems[i])
      }
    }
    this.problems = problems
  }
  else {
    this.problems = this.allProblems
  }
    // this.router.navigate(
      // ['problems'],
      // {
        // queryParams: { level: diff }
      // }
    // )
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

  diffFilter() {
    const diff = document.getElementsByClassName("group1")
    console.log(diff)
    for (let i = 0; i < diff.length; i ++) {
      console.log(diff[i])
    }
  }

  ngOnInit(): void {
  }

}
