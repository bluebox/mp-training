import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MovieInterface } from 'src/app/interface/movie';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { ViewComponent } from '../view/view.component';

@Component({
  selector: 'app-hollywood',
  templateUrl: './hollywood.component.html',
  styleUrls: ['./hollywood.component.css']
})
export class HollywoodComponent implements OnInit {
  public movies!:MovieInterface[]
  public hmovies:string[]=["h1","h2","h3","h4"]
  longText = `The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog
  from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was
  originally bred for hunting.`;

  constructor(private movie:MoviedataService,private router:Router,private dialog:MatDialog) { }

  ngOnInit(): void {
    this.movie.getMovieByLang('English').subscribe(data=>this.movies=data)
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
