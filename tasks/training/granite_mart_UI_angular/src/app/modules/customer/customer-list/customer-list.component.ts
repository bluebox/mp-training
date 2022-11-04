import { Component, OnInit } from '@angular/core';
import { DataServiceService } from 'src/app/services/data-service.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  
  Customers_list:any=""

  loginValue:any
  constructor(private service:DataServiceService,private router:Router, private aroute:ActivatedRoute) { }

  ngOnInit(): void {
    let username:any
    this.aroute.params.subscribe(data=>username=data["username"])
    this.service.getCustomer(username.toString()).subscribe(data=>{
      this.Customers_list=data;
      console.log()
    },error=>{alert(error.status);
      this.loginValue='false';
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
