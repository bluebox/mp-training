import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  loginValue:any=localStorage.getItem('login')
  constructor() { }

  ngOnInit(): void {
  }

  logout(){
    alert("logging out...")
    sessionStorage.removeItem('token')
  }
}
