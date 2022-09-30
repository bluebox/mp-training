import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Home } from '../Home';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {
  info: Home[];
  @Output()
  data: EventEmitter<Home[]> = new EventEmitter<Home[]>();
  message: string | undefined;
  constructor() { 
    this.message = "I am message from child";
    this.info = [
      {
      Name: "Engineer",
      Post: "Associate S E",
      Salary: 50000,
      Active: true
      },
      {
      Name: "Developer",
      Post: "Sr. Developer",
      Salary: 75000,
      Active: true
      },
      {
      Name: "Tester",
      Post: "Software Testing",
      Salary: 45000,
      Active: true
      },
      
    ];
  }
  passData(){
    this.data.emit(this.info)
  }
  ngOnInit(): void {
  }

}
