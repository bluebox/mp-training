import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-result',
  templateUrl: './view-result.component.html',
  styleUrls: ['./view-result.component.css']
})
export class ViewResultComponent implements OnInit {
i:any
j:any
percentage:any
  constructor() { }

  ngOnInit(): void {
    this.i=localStorage.getItem('score')
    this.j=localStorage.getItem('length')
    this.percentage=(this.i/this.j)*100

  }

}
