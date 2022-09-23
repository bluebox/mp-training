import { HttpService } from './../../http.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscriber, Subscription } from 'rxjs';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  response: any
  obs: Subscription=Subscription.EMPTY

  constructor(private http: HttpClient, private service : HttpService) {

    this.obs=this.service.getUsers().subscribe(data => {this.response=data;console.log(data)}
      )
   }

  ngOnInit(): void {
  }
}
