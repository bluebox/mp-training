import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cheader',
  templateUrl: './cheader.component.html',
  styleUrls: ['./cheader.component.css']
})
export class CheaderComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit(): void {
  }
  logout(){
    if (confirm('Are you sure you want to log out?')) {
      sessionStorage.removeItem('cuser');
      sessionStorage.removeItem('freelancer_proposals');
      sessionStorage.removeItem('contractDetails');
      alert('logged out successfully');
      this.router.navigate(['']);

    }
  }
}
