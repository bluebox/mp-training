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

  tags: any;
  p: any;
  pages: any;
  problems!: any
  accuracies: any;
  sortBasedOnDifficulty: Number = 0;
  sortBasedOnAccuracy: Number = 0;
  searchWord = '';
  currentPage: any = 1;

  filterForm = this.fb.group({
    diff: ['', Validators.required]
  })
  tagsToggle: boolean = true;

  constructor(public route: ActivatedRoute, public fb: FormBuilder, public service: RegisterService, private http: HttpClient, private router: Router) {
    this.service.getProblems().subscribe((data: any) => {
      this.service.getTags().subscribe((data) => {
        this.tags = data
        console.log(data);
        
      })
      this.problems = data
      this.p = []
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
      // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
      this.pages = Math.ceil(Number((this.problems.length / 3).toFixed(2)));
    })
  }

  remove_tag(id: any) {
    console.log("clicked", id.target.classList[0]);
    let name = id.target.classList[0]
    let child = document.getElementById(name) as HTMLDivElement
    const tags = document.querySelector(".selected-tags") as HTMLDivElement
    tags.removeChild(child)
  }

  apply_filters() {
    const search = document.getElementById("search") as HTMLInputElement
    const difficulty_level = document.getElementById("difficulty-level") as HTMLSelectElement
    const tags = document.querySelector(".selected-tags") as HTMLDivElement
    console.log(search.value, difficulty_level.value, tags);
    let arr = []
    for (let i = 0; i < tags.children.length; i ++) {
      arr.push(tags.children[i].children[0].innerHTML)
    }
    console.log(arr);
    let data = {
      'search': search.value,
      'diff': Number(difficulty_level.value),
      'tags': arr
    }
    this.service.apply_filters(data).subscribe((data: any) => {
      console.log(data);
      this.problems = data
      this.p = []
      this.currentPage = 1
    for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
      this.p.push(this.problems[i])
    }
    console.log(this.problems.length);
      console.log("///", Math.ceil(Number((data.length / 3).toFixed(2))));
      
      this.pages = Math.ceil(Number((data.length / 3).toFixed(2)));
      if (this.pages == 0) {
        this.pages = 1;
      }
    })
  }

  add_tag() {
    console.log("called");
    const tags = document.querySelector(".selected-tags") as HTMLDivElement
    const selectedTag = document.querySelector(".tags") as HTMLSelectElement
    let f = 0
    for (let i = 0; i < tags.children.length; i ++) {
      if (selectedTag.value == tags.children[i].innerHTML) {
        f = 1;
      }
    }
    if (f == 0 && selectedTag.value != "") {
      const element = document.createElement("div")
      element.setAttribute("id", selectedTag.value)
      const tag = document.createElement("span")
      tag.innerHTML = selectedTag.value
      element.appendChild(tag)
      const btn = document.createElement("button")
      btn.innerHTML = "X"
      btn.setAttribute("class", selectedTag.value)
      btn.addEventListener("click", this.remove_tag)
      element.appendChild(btn)
      tags.appendChild(element)
    }
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

  // submitSearch() {
  //   this.submit()
  //   console.log(this.searchWord);
  //   let word: any = this.searchWord.trim()
  //   console.log(word);
  //   let keywords = word.split(" ")
  //   for (let i = 0; i < keywords.length; i ++) {
  //     keywords[i] = keywords[i].toLowerCase();
  //   }
  //   console.log(keywords);
  //   let problems = []
  //   let p = []
  //   if (this.flag == true) {
  //     p = this.allProblems
  //   }
  //   else {
  //     p = this.problems
  //   }
  //   for (let i = 0; i < p.length; i ++) {
  //     for (let j = 0; j < keywords.length; j ++) {
  //       if (p[i].problem_name.toLowerCase().includes(keywords[j])) {
  //         problems.push(p[i])
  //         break
  //       }
  //     }
  //   }
  //   this.problems = problems
  //   this.p = []
  //   for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
  //     this.p.push(this.problems[i])
  //   }
  //   this.pages = Math.ceil(Number((this.problems.length / 3).toFixed(2)));
  //   if (this.pages == 0) {
  //     this.currentPage = 0;
  //   }
  //   // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
  // }

  // submit() {
  //   this.currentPage = 1
  //   console.log(1);

  //   const diffs = document.getElementById("select") as HTMLSelectElement
  //   let diff = Number(diffs.value)
  //   // for (let i = 0; i < diffs.children.length; i ++) {
  //   //   const inp = diffs.children[i] as HTMLInputElement
  //   //   if (inp.checked) {
  //   //     diff = Number(inp.value)
  //   //   }
  //   // }
    
    
  //   console.log(diff);
  //   let problems = []
  //   if (diff != -1) {
  //   for (let i = 0; i < this.allProblems.length; i ++) {
  //     if (this.allProblems[i].difficulty_level == diff) {
  //       problems.push(this.allProblems[i])
  //     }
  //   }
  //   // for (let i = 0; i < this.p.length; i ++) {
  //   //   if (this.p[i].difficulty_level == diff) {
  //   //     problems.push(this.p[i])
  //   //   }
  //   // }
  //   this.problems = problems
  //   this.pages = Math.ceil(Number((this.problems.length / 3).toFixed(2)));
  //   console.log("//////");
  //   if (this.pages == 0) {
  //     this.currentPage = 0;
  //   }
    
  //   console.log(this.pages);
    
  //   this.p = []
  //   for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
  //     this.p.push(this.problems[i])
  //   }
  //   // this.p = problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
  //   this.flag = false;
  // }
  // else {
  //   this.flag = true;
  //   this.problems = this.allProblems
  //   this.pages = Math.ceil(Number((this.problems.length / 3).toFixed(2)));
  //   if (this.pages == 0) {
  //     this.currentPage = 0;
  //   }
  //   this.p = []
  //   for (let i = (this.currentPage - 1) * 3; i < ((this.currentPage - 1) * 3) + 3; i ++ ) {
  //     this.p.push(this.problems[i])
  //   }
  //   // this.p = this.problems.splice((this.currentPage - 1) * 3, ((this.currentPage - 1) * 3) + 3)
  // }
  //   // this.router.navigate(
  //     // ['problems'],
  //     // {
  //       // queryParams: { level: diff }
  //     // }
  //   // )
  // }

  toggleTagIndicator() {
    console.log("//////");
    this.tagsToggle = !this.tagsToggle;
    const ele = document.querySelector('.tag') as HTMLSpanElement
    console.log(ele);
    
    if (this.tagsToggle) {
      ele.classList.remove("hide-tag")
    }
    else {
      ele.classList.add("hide-tag")
    }
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
