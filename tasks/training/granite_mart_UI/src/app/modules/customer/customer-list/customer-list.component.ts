import { Component, OnInit } from '@angular/core';
import { DataServiceService } from 'src/app/services/data-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  
  Customers_list:any

  loginValue:any=localStorage.getItem('login')
  constructor(private service:DataServiceService,private router:Router) { }

  ngOnInit(): void {
    this.service.getCustomers().subscribe(data=>{
      this.Customers_list=data;
      console.log(data)
    });
  }

  onclick(ele:any){
    console.log(ele)
      this.router.navigate(['viewCustomer/',ele.toString()]);
  }

  editProfile(username:any){
      this.router.navigate(['customerEdit',username])
  }

  deleteProfile(username:any){
    this.service.deleteCustomer(username).subscribe(data=>console.log(data))
    this.router.navigate(['customerLogin'])
  }

}
