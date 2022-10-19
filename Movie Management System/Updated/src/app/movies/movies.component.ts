import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewComponent } from '../info/components/view/view.component';
import { MovieInterface } from '../interface/movie';
import { MoviedataService } from '../services/moviedata.service';
import { UserdataService } from '../services/userdata.service';

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

  constructor(private movie:MoviedataService,private router:Router,private route:ActivatedRoute,private dialog:MatDialog,private user:UserdataService) { }

  ngOnInit(): void {
    this.user.getuser().subscribe(data=>{console.log(data);
      if(data.User_id>0){
        this.user.setstatus(true)
        console.log(this.user.status)
      }
    else{
      this.user.setstatus(false)
      console.log(this.user.status)
    }})
    this.movie.getMovies()
    .subscribe((data)=>this.movies=data)
  }


  moviepage(){
    this.router.navigate(['moviename'],{relativeTo:this.route})
  }


  openview(mname:string,mlang:string,mdetails:string,mreleasedate:Date,mcat:string,mcast:string){
    console.log()
    let dialogRef = this.dialog.open(ViewComponent, {
      data: { name:mname,lang:mlang,det:mdetails,date:mreleasedate,category:mcat,cast:mcast },
    });
  }


  openBooking(id:number){
      this.router.navigate(['booking/theatrelist',id])}

}
