import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  is_staff = localStorage.getItem('is_staff')
  username = localStorage.getItem('username')
  streak!: any;

  constructor(public service: RegisterService) { 
    this.service.getStreak().subscribe((data) => {
      this.streak = data;
      console.log(data);
    })
   }

  ngOnInit(): void {
  }

}
