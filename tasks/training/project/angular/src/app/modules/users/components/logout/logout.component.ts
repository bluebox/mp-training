import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpServiceService } from '../../http-service.service';
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
   username = this.http.getData('username')
  constructor(private http: HttpServiceService,
    private router: Router) { }
  ngOnInit(): void {
  }
  logoutUser(){
    this.http.logoutUser({ 'username': this.username }).subscribe(data =>{
      console.log(data);
      this.http.removeData("username")
      this.router.navigate([''])


    })
  }

}
