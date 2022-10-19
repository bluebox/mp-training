import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';
import {MatIconModule} from '@angular/material/icon';
@Component({
  selector: 'app-flogin-page',
  templateUrl: './flogin-page.component.html',
  styleUrls: ['./flogin-page.component.css']
})
export class FloginPageComponent implements OnInit {
  details: any;

  constructor(private service : ServiceService) { 
  }

  ngOnInit(): void {
    this.service.freelancerDetails(this.token).subscribe((details: any) => {
      this.details = details;
      window.localStorage.setItem('fuser', JSON.stringify(details));
      this.parse_data = details

    });
  }
  logout(): void { 
    window.sessionStorage.removeItem('token');
    window.localStorage.removeItem('fuser');
   }  
  data : any = localStorage.getItem('fuser')
  token : any = sessionStorage.getItem('token')
  parse_data : any
}
