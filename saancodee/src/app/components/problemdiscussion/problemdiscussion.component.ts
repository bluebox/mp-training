import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-problemdiscussion',
  templateUrl: './problemdiscussion.component.html',
  styleUrls: ['./problemdiscussion.component.css']
})
export class ProblemdiscussionComponent implements OnInit {

  id!:any;
  discussions!:any;

  constructor(private router:Router, private route: ActivatedRoute,  private http: HttpClient) { 
    this.id = route.snapshot.params['id'];
    let url = `http://127.0.0.1:8000/api/discussions/${this.id}`
    const headers = new HttpHeaders(
        {
          'Content-Type': 'application/json',
          'Authorization': 'token ' + localStorage.getItem('token')
        }
      )
    this.http.get(url, {headers}).subscribe((data) => {
      console.log(data)
      this.discussions = data
    })
   }

   onSubmit(id:any) {
    this.router.navigate(['problems/discussions/', this.id, id])
   }

   onDiscussionPost() {
    this.router.navigate(['post-discussion/', this.id]);
   }

  ngOnInit(): void {
  }

}