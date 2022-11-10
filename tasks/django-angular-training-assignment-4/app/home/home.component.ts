import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  response: any;
  id: string = "";
  title!: string;
  body!: string;
  // url: string = "https://jsonplaceholder.typicode.com/posts/";
  obs: Subscription = new Subscription;

  constructor(private http:HttpClient, private route:ActivatedRoute, private service: HttpService) { 
    this.id = this.route.snapshot.params['id'];
    this.service.findWeather(this.id).subscribe((data) => {
      console.log(data);
      this.response = data;
      this.title = this.response['title'];
      this.body = this.response['body'];
    })
   }

  ngOnInit(): void {
  }

}
