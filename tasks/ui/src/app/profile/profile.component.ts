import { identifierName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  response: any;
  userid: any;

  constructor(private service:ServicesService) { 
    let userdetails=sessionStorage.getItem('userdetails')
    if(userdetails){
      this.userid=JSON.parse(userdetails)
    }
    this.service.getProfile(this.userid.emp_id).subscribe(data=> {
      this.response=data
    })
  }

  ngOnInit(): void {
  }

}
