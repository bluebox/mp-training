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

  p: any;
  pages: any = 0;
  flag: boolean = true;
  allProblems!: any;
  problems!: any
  accuracies: any;
  sortBasedOnDifficulty: Number = 0;
  sortBasedOnAccuracy: Number = 0;
  searchWord = '';
  currentPage: any = 1;

  filterForm = this.fb.group({
    diff: ['', Validators.required]
  })

  constructor(public route: ActivatedRoute, public fb: FormBuilder, public service: RegisterService, private http: HttpClient, private router: Router) {
    this.service.getProblems().subscribe((data: any) => {
      this.allProblems = data
      this.problems = data
      this.p = []
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
      // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
      this.pages = Math.ceil(Number((this.allProblems.length / 3).toFixed(2)));
      console.log(data, Number((this.allProblems.length / 3).toFixed(2)))
    })
  }

  move_to_previous_page() {
    this.currentPage -= 1;
    console.log(this.problems)
    this.p = []
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
    // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
    // this.problems = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
  }

  move_to_next_page() {
    this.currentPage += 1;
    console.log(this.problems)
    this.p = []
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
    // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
    // this.problems = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
  }

  submitSearch() {
    this.submit()
    console.log(this.searchWord);
    let word: any = this.searchWord.trim()
    console.log(word);
    let keywords = word.split(" ")
    for (let i = 0; i < keywords.length; i ++) {
      keywords[i] = keywords[i].toLowerCase();
    }
    console.log(keywords);
    let problems = []
    let p = []
    if (this.flag == true) {
      p = this.allProblems
    }
    else {
      p = this.problems
    }
    for (let i = 0; i < p.length; i ++) {
      for (let j = 0; j < keywords.length; j ++) {
        if (p[i].problem_name.toLowerCase().includes(keywords[j])) {
          problems.push(p[i])
          break
        }
      }
    }
    this.problems = problems
    this.p = []
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
    this.pages = Math.ceil(Number((this.problems.length / 3).toFixed(2)));
    // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
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
    // for (let i = 0; i < this.p.length; i ++) {
    //   if (this.p[i].difficulty_level == diff) {
    //     problems.push(this.p[i])
    //   }
    // }
    this.problems = problems
    this.pages = Math.ceil(Number((this.problems.length / 3).toFixed(2)));
    this.p = []
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
    // this.p = problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
    this.flag = false;
  }
  else {
    this.flag = true;
    this.problems = this.allProblems
    this.pages = Math.ceil(Number((this.problems.length / 3).toFixed(2)));
    this.p = []
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
    // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
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
      this.p.sort((a:any, b:any) => a.accuracy - b.accuracy)
    }
    else if(this.sortBasedOnAccuracy == 1) {
      this.sortBasedOnAccuracy = -1;
      this.p.sort((a:any, b:any) => b.accuracy - a.accuracy)
    }
    else{
      this.sortBasedOnAccuracy = 1;
      this.p.sort((a:any, b:any) => a.accuracy - b.accuracy)
    }
    
    
    // this.sortProblems();
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
