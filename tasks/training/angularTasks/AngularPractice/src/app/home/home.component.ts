import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  rdata:any='';

  constructor(private ar:ActivatedRoute) {

    this.ar.params.subscribe(data=>this.rdata=data['id']);
     }
     
  ngOnInit(): void {
  }
  

}
