// import { Dialog } from '@angular/cdk/dialog';
import {MatDialog} from '@angular/material/dialog';

import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MovieInterface } from 'src/app/interface/movie';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { ViewComponent } from '../view/view.component';
import { ActivatedRoute, Router } from '@angular/router';
// import { MovieserviceService } from 'src/app/movieservice.service';




@Component({
  selector: 'app-bollywood',
  templateUrl: './bollywood.component.html',
  styleUrls: ['./bollywood.component.css']
})
export class BollywoodComponent implements OnInit {
  public movies:MovieInterface[]=[]
  longText = `The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog
  from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was
  originally bred for hunting.`;


  constructor(private movie:MoviedataService,private dialog:MatDialog,private route:ActivatedRoute,private router:Router){ }

  ngOnInit(): void {
    this.movie.getMovies()
    .subscribe((data)=>this.movies=data)
  }
  // @Output() emitter:EventEmitter<string> = new EventEmitter<string>();
  // emit():void{
  //   this.emitter.emit(id);
  openview(mid:number,mname:string,mlang:string,mdetails:string,mreleasedate:Date,mcat:string,mcast:string){
    // this.movie.getSingleMovie(m-1)
    // .subscribe((data)=>this.mov=data)
    console.log()
    let dialogRef = this.dialog.open(ViewComponent, {
      data: { id:mid,name:mname,lang:mlang,det:mdetails,date:mreleasedate,category:mcat,cast:mcast },
    });
  }
  openBooking(id:number){
    this.router.navigate(['booking/theatrelist', id]);
  }

}
