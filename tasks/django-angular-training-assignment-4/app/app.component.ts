import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  response: any;
  id: string = "";
  url: string = "https://jsonplaceholder.typicode.com/posts/";
  // headers = {
  //   "X-RapidAPI-Key": "3a73a0d370msh60b84744b0c5a4fp1ff88djsn90dd6d9cde78",
  //   "X-RapidAPI-Host": "open-weather13.p.rapidapi.com"
  // }

  // options = {
  //   headers: new Headers(this.headers),
  // };

  constructor(private http: HttpClient, private route: Router) {

  }

  findWeather() {
    this.http.get(this.url + this.id).subscribe((data) => {
      console.log(this.id)
      this.response = data;
      this.id = "";
      console.log(this.response);
    })
    this.route.navigate(['search', this.id])
  }

  title = 'weather';
}
