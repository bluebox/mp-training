import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fheader',
  templateUrl: './fheader.component.html',
  styleUrls: ['./fheader.component.css']
})
export class FheaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  logout(): void { 
    window.sessionStorage.removeItem('token');
    window.localStorage.removeItem('fuser');
   }  
}
