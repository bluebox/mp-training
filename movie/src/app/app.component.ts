import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import { ViewComponent } from './info/components/view/view.component';
import { MovieInterface } from './interface/movie';
import { LoginComponent } from './login/login.component';
import { MoviedataService } from './services/moviedata.service';
import { SignupComponent } from './signup/signup.component';

ViewComponent
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public nav:FormGroup=new FormGroup({
    
  })
  public movies:MovieInterface[]=[]
  title = 'movie';
  constructor(public dialog: MatDialog,public movie:MoviedataService) {}
  ngOnInit(): void {
  this.movie.getMovies().subscribe(data=>this.movies=data)
}
openview(mid:number,mname:string,mlang:string,mdetails:string,mreleasedate:Date,mcat:string,mcast:string){
  // this.movie.getSingleMovie(m-1)
  // .subscribe((data)=>this.mov=data)
  console.log()
  let dialogRef = this.dialog.open(ViewComponent, {
    data: { id:mid,name:mname,lang:mlang,det:mdetails,date:mreleasedate,category:mcat,cast:mcast },
  });
}
  openDialog() {
    this.dialog.open(SignupComponent);
  }
  openLogin() {
    this.dialog.open(LoginComponent);
  }
}
