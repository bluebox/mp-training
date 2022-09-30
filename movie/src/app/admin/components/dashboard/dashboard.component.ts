import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

Router
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private router:Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
  }
  GotoAltermovies(){
    this.router.navigate(['altermovies'],{relativeTo:this.route})
  }
  GotoAlterusers(){
    this.router.navigate(['alterusers'],{relativeTo:this.route})
  }

}
