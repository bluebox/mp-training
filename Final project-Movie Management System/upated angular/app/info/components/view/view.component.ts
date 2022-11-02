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
  public id!:number
  constructor(private movie:MoviedataService,@Inject(MAT_DIALOG_DATA) public data: {id:number,name:string,lang:string,det:string,date:Date,category:string,cast:string},private router:Router) {this.id=this.data.id }

  ngOnInit(): void {

    console.log(this.id)

}
openBooking(){
  console.log("working!",this.id)
  this.router.navigate(['booking/theatrelist', this.data.id])
}
  }
 

