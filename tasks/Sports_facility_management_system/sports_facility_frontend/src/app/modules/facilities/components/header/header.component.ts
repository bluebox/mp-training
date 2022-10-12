import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FacilityService } from '../../services/facility.service';
import { ParticularsportComponent } from '../particularsport/particularsport.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  searchresults: any;
  text: any;
  sports: any;
  user_id: any;
  user_verfied: boolean = false;
  token_msg: any;
  constructor(private service: FacilityService, private router: Router) {}

  ngOnInit(): void {
    this.service.getSports().subscribe((data) => {
      this.sports = data;
    });

    let refresh_token = localStorage.getItem('refresh_token');

    this.service.CheckRefreshToken(refresh_token).subscribe((data) => {
      this.token_msg = data;
      if (this.token_msg != 'access token does not exist') {
        this.user_verfied = true;
        this.user_id = this.token_msg;
      }
    });
  }
  logout(): void {
    localStorage.removeItem('refresh_token');
    this.user_verfied = false;
    this.router.navigate(['facilities/hyderabad']);
  }
  search(text: any) {
    // this.text = event.target.value;
    console.log(text);
    if (text.length > 2) {
      this.service.searchFacility(text).subscribe((data) => {
        this.searchresults = data;
        console.log(data);
      });
    } else {
      this.searchresults = [];
    }
  }
  facilitysearch(id: any) {
    console.log(id);
    this.router.navigate(['facilities/hyderabad', String(id)]);
  }

  sportid(id: any) {
    console.log(id);
    this.router.navigate(['facilities/hyderabad/sport', id]);
    
  }

  OpenUserHome() {
    this.router.navigate(['user/home', this.user_id]);
  }
}
