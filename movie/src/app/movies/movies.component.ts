import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewComponent } from '../info/components/view/view.component';
import { MovieInterface } from '../interface/movie';
import { MoviedataService } from '../services/moviedata.service';

// import { MovieserviceService } from '../movieservice.service';
ViewComponent
ActivatedRoute
@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  public movies:MovieInterface[]=[]

  constructor(private movie:MoviedataService,private router:Router,private route:ActivatedRoute,private dialog:MatDialog) { }

  ngOnInit(): void {
    this.movie.getMovies()
    .subscribe((data)=>this.movies=data)
  }
  // getInfo(i:number){
  //   this.router.navigate(['movieInfo'])
  // }
  moviepage(){

    this.router.navigate(['moviename'],{relativeTo:this.route})
  }
  openview(mname:string,mlang:string,mdetails:string,mreleasedate:Date,mcat:string,mcast:string){
    // this.movie.getSingleMovie(m-1)
    // .subscribe((data)=>this.mov=data)
    console.log()
    let dialogRef = this.dialog.open(ViewComponent, {
      data: { name:mname,lang:mlang,det:mdetails,date:mreleasedate,category:mcat,cast:mcast },
    });
  }
  openBooking(id:number){
    this.router.navigate(['booking/theatrelist',id])
  }

}
