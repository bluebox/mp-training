import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ServercomunicationService } from '../servercomunication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  logOut: FormGroup = new FormGroup({});

  constructor(private api:ServercomunicationService,private router:Router) { }

  ngOnInit(): void {
    this.saveDetails();
  }
saveDetails(){
  this.api.logout().subscribe(
    (data:any)=>{
      console.log(data);
    },
    (error: any)=>{
  console.log(error);
})
}
relocate(){
  this.router.navigate([''])

}
}
