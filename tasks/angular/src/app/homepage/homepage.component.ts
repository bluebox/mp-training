import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  loginAndLogOutCheck:any;
  constructor(private router:Router, private userdata:UserserviceService) {
    this.loginAndLogOutCheck = true
    this.userdata.loginStatus(this.loginAndLogOutCheck)
   }

  ngOnInit(): void {
   
  }

}
