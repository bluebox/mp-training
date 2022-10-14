import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  count:number=0;
  currentURL:string='';
  constructor(private router:Router) { 
    
  }

  ngOnInit(): void {
  }
  createfacility():void{
    this.currentURL = window.location.href;
    if (this.currentURL=="http://localhost:4200/admin/home/createfacility"){
      this.router.navigate(['admin/home'])
    }
    else{
      this.router.navigate(['admin/home/createfacility'])
    }
  }
  viewfacility():void{
    this.currentURL = window.location.href;
    if (this.currentURL=="http://localhost:4200/admin/home/viewfacility"){
      this.router.navigate(['admin/home'])
    }
    else{
      this.router.navigate(['admin/home/viewfacility'])
    }
  }

}
