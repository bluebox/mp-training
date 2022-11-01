import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminheader',
  templateUrl: './adminheader.component.html',
  styleUrls: ['./adminheader.component.css']
})
export class AdminheaderComponent implements OnInit {
  admin_verfied:boolean = false;
  constructor(private router: Router,) { }

  ngOnInit(): void {
  }
  logout(): void {
    localStorage.removeItem('refresh_token');
    this.admin_verfied = false;
    this.router.navigate(['facilities/hyderabad']);
  }

}
