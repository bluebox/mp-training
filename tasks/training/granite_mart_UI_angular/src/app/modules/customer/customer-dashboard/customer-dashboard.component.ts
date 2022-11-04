import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.css']
})
export class CustomerDashboardComponent implements OnInit {

  loginValue:any
  constructor(private router:Router, private cservice:CookieService,private service:DataServiceService) {
    this.loginValue=this.cservice.get('login')
   }

  ngOnInit(): void {
  }

  logout(){
    this.service.logout().subscribe(data=>console.log(data),err=>console.log(err))
      this.router.navigate(['customerLogin'])
  }
  profile(){
    this.router.navigate(['viewCustomer',this.cservice.get('username')])
  }

  cart(){
    console.log(this.cservice.get('username'))
    this.router.navigate(['viewCart',this.cservice.get('username')])
  }

}
