import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { HttpserviceService } from 'src/app/httpservice.service';
// import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // subscription : Subscription = Subscription.EMPTY
  branches: any;
  constructor() { }

  ngOnInit(): void {
    // this.subscription =this.http.getBranch().subscribe((data) =>{this.branches;console.log(this.branches)});
  }

  alertmessage(){
    alert("You need to register to book an appointment")
  }
}
