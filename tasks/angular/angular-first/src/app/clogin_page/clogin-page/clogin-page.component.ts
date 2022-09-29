import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clogin-page',
  templateUrl: './clogin-page.component.html',
  styleUrls: ['./clogin-page.component.css']
})
export class CloginPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  data : any = window.sessionStorage.getItem('cuser');
  parse_data = JSON.parse(this.data)

}
