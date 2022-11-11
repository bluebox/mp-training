import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { MovieInterface } from 'src/app/interface/movie';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { ViewComponent } from '../view/view.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tollywood',
  templateUrl: './tollywood.component.html',
  styleUrls: ['./tollywood.component.css']
})
export class TollywoodComponent implements OnInit {

  public movies:MovieInterface[]=[]

  constructor(private movie:MoviedataService,private dialog:MatDialog,private route:ActivatedRoute,private router:Router){ }

  ngOnInit(): void {
    this.movie.getMovieByLang('Telugu')
    .subscribe((data)=>this.movies=data)
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


