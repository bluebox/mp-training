import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
 user:string='';
 pass:string='';
 adminform:any=FormGroup
 name:any;

 constructor(private router:Router) { }

  ngOnInit(): void {


    this.adminform = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),

    });
    this.name=localStorage.getItem("name")
  }



}



