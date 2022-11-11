// import { Dialog } from '@angular/cdk/dialog';
import {MatDialog} from '@angular/material/dialog';

import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MovieInterface } from 'src/app/interface/movie';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { ViewComponent } from '../view/view.component';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-bollywood',
  templateUrl: './bollywood.component.html',
  styleUrls: ['./bollywood.component.css']
})
export class BollywoodComponent implements OnInit {
  public movies:MovieInterface[]=[]
  public hindi:MovieInterface[]=[]
  public telugu:MovieInterface[]=[]
  public english:MovieInterface[]=[]



  constructor(private movie:MoviedataService,private dialog:MatDialog,private route:ActivatedRoute,private router:Router){ }

  ngOnInit(): void {
    this.movie.getMovies()
    .subscribe((data)=>{this.movies=data
    for(let i=0;i<this.movies.length;i++){
      if(this.movies[i].Movie_lang==='Hindi'){
        this.hindi.push(this.movies[i])
      }
      else if(this.movies[i].Movie_lang==='English'){
        this.english.push(this.movies[i])
      }
      else if(this.movies[i].Movie_lang==='Telugu'){
        this.telugu.push(this.movies[i])
      }

    }})
  }

  openview(mid:number,mname:string,mlang:string,mdetails:string,mreleasedate:Date,mcat:string,mcast:string){ 
    let dialogRef = this.dialog.open(ViewComponent, {
      data: {'id':mid,name:mname,lang:mlang,det:mdetails,date:mreleasedate,category:mcat,cast:mcast },
    });
  }

  openBooking(id:number){
    this.router.navigate(['booking/theatrelist', id]);
  }

}
