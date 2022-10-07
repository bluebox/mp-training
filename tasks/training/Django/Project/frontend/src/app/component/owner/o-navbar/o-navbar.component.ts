import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GeneralService } from 'src/app/general.service';

@Component({
  selector: 'app-o-navbar',
  templateUrl: './o-navbar.component.html',
  styleUrls: ['./o-navbar.component.css']
})
export class ONavbarComponent implements OnInit {

  constructor( private service  : GeneralService, private router : Router ) { }

  ngOnInit(): void {
  }

  onLOgOut(){
    this.service.logOutOwner().subscribe({
      next: (data) => {
        console.log(data);
      }
    })
    this.router.navigate([''])
  }
}
