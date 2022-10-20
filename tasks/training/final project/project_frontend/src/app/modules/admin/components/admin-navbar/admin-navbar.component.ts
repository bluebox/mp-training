import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent implements OnInit {

  constructor(private router:Router, private http:AdminServiceService) { }

  ngOnInit(): void {
  }
  click1(){
    this.router.navigate(['admin'])
  }
  click2(){
    this.router.navigate(['admin/display-student'])
  }
  click3(){
    this.router.navigate(['admin/display-teacher'])
  }
  clickOut(){
    this.router.navigate([''])
  }

}
