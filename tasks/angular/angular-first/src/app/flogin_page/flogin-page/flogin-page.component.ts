import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-flogin-page',
  templateUrl: './flogin-page.component.html',
  styleUrls: ['./flogin-page.component.css']
})
export class FloginPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  data : any = sessionStorage.getItem('fuser')
  parse_data = JSON.parse(this.data)
}
