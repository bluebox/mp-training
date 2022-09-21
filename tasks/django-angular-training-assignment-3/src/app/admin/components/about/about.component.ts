import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  name!:String;


  constructor(private route:ActivatedRoute) {
    this.name = this.route.snapshot.params['name'];
   }

  ngOnInit(): void {
  }

}