import { Router } from '@angular/router';
import { GeneralService } from 'src/app/general.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private service  : GeneralService, private router : Router) { }

  ngOnInit(): void {
  }

  onLogOut(){
  this.service.logOutCustomer().subscribe({
    next: (data) => {
      console.log(data);
      window.sessionStorage.removeItem('token')
    }
  })
  this.router.navigate([''])
}
}
