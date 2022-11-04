import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  isAdmin='False'
  loginValue='true'
  username:any
  constructor(private router:Router, private cservice:CookieService, private service:DataServiceService) {
    this.username=this.cservice.get('username')
    this.isAdmin=this.cservice.get('isAdmin')
    this.loginValue= this.cservice.get('login')
   }

  ngOnInit(): void {
  }

  logout(){
    this.service.logout().subscribe(data=>console.log(data),err=>alert(err))
    this.router.navigate(['customerLogin'])
    
  }

  profile(){
    this.router.navigate(['viewCustomer',localStorage.getItem('username')])
  }

  customerList(){
    this.router.navigate(['customersList',this.username])
  }
  addItem(){
    this.router.navigate(['itemRegistration'])
  }
}
