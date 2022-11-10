import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent implements OnInit {

  admin: any;

  constructor() { 
    console.log(localStorage);
    
    this.admin = localStorage.getItem('admin')
    console.log("//////");
    
    console.log(this.admin);
   }

  ngOnInit(): void {
  }

}
