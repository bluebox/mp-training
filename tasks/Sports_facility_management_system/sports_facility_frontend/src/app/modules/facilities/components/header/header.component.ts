import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  sports: any;
  user_id: any;
  user_verfied: boolean = false;
  admin_verfied: boolean = false;
  token_msg: any;

  constructor(private service: FacilityService, private router: Router) {}

  ngOnInit(): void {
    this.service.getSports().subscribe((data) => {
      this.sports = data;
    });
    
    var token;
    if (localStorage.getItem('refresh_token') === null) {
      token = '';
      
    }
    else{
      token = localStorage.getItem('refresh_token')
    }
    this.service.CheckRefreshToken(token).subscribe(
      (data) => {
        let res = JSON.stringify(data);
        let Parsed = JSON.parse(res);
        if (Parsed.is_admin) {
          this.admin_verfied = true;
          this.router.navigate(['admin/home']);
        } else {
          this.user_verfied = true;
          this.user_id = Parsed.user_id;
        }
      },
      (err) => {
        // console.log(err);
        this.admin_verfied = false;
        this.user_verfied = false;

      }
    );
  }

  logout(): void {
    localStorage.removeItem('refresh_token');
    this.user_verfied = false;
    this.router.navigate(['facilities/hyderabad']);
  }

  OpenUserHome() {
    this.router.navigate(['user/home', this.user_id]);
  }
}
