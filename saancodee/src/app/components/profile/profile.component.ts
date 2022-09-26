import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username!: string

  constructor(private route: ActivatedRoute, private http: HttpClient) { 
    this.username = this.route.snapshot.params['username']
    console.log(localStorage.getItem('currentUser'))
    const headers = new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Autherization': 'token ' + String(localStorage.getItem('currentUser'))
      }
    )
    this.http.get("http://127.0.0.1:8000/api/profile/" + this.username, {headers: headers}).subscribe((data) => {
      console.log(data)
    })
    
    // loadProfile() {
    //   const headers = new HttpHeaders(
    //     {
    //       'Content-Type': 'application/json',
    //       'Autherization': 'token ' + String(localStorage.getItem('currentUser'));
    //     }
    //   )
    //   this.http.get("http://127.0.0.1:8000/api/profile/" + this.username, {headers: headers})
    // }
  }


  ngOnInit(): void {
  }

}
