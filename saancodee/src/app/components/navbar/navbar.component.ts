import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  is_staff = localStorage.getItem('is_staff')

  constructor(public service: RegisterService) { }

  ngOnInit(): void {
  }

}
