import { Component, OnInit } from '@angular/core';
import { MoviedataService } from 'src/app/services/moviedata.service';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  constructor(private movie:MoviedataService,@Inject(MAT_DIALOG_DATA) public data: {id:number,name:string,lang:string,det:string,date:Date,category:string,cast:string},private router:Router) { }

  ngOnInit(): void {

}
openBooking(id:number){
  this.router.navigate(['booking/theatrelist',id])

}
  }
 

