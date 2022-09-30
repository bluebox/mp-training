import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServicesService } from 'src/app/services/user-services.service';

@Component({
  selector: 'app-user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.scss']
})
export class UserLogoutComponent implements OnInit {

  constructor(private userService:UserServicesService,  private route:Router) { }

  ngOnInit(): void {
    this.userService.logout();
    this.route.navigate(['/user/login']);

  }

}
