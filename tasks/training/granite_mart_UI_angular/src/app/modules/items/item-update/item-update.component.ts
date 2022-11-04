import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-item-update',
  templateUrl: './item-update.component.html',
  styleUrls: ['./item-update.component.css']
})
export class ItemUpdateComponent implements OnInit {


  loginValue:any=localStorage.getItem('login')
  constructor() { }

  ngOnInit(): void {
  }

}
