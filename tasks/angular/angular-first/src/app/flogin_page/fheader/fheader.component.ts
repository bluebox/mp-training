import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-fheader',
  templateUrl: './fheader.component.html',
  styleUrls: ['./fheader.component.css']
})
export class FheaderComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit(): void {
  }
  logout(): void {
    if (confirm('Are you sure you want to log out?')) {
      window.sessionStorage.removeItem('token');
      window.localStorage.removeItem('fuser');
      alert('You have been logged out')
      this.router.navigate(['']);
    }
  }
}
