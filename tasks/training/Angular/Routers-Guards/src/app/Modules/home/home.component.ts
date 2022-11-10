import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  recivedData: string=''

  constructor(private rcvDta: ActivatedRoute) {
    this.rcvDta.params.subscribe(data=>this.recivedData=data['id'])
  }

  ngOnInit(): void {
  }

}
