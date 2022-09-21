import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor(private data: Router) { }

  ngOnInit(): void {
  }

  send(){
    this.data.navigate(['admin/home','sentdata'])
  }

}
