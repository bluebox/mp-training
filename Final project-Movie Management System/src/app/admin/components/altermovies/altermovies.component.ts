import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MovieInterface } from 'src/app/interface/movie';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { MovieFormComponent } from '../movie-form/movie-form.component';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-altermovies',
  templateUrl: './altermovies.component.html',
  styleUrls: ['./altermovies.component.css']
})
export class AltermoviesComponent implements OnInit {
  public movies:MovieInterface[]=[]
  constructor(private movie:MoviedataService,private http:HttpClient,private dialog:MatDialog) {

   }
   ngOnInit(): void {
    this.getmovies()
 
   }


  getmovies(){
    return  this.movie.getMovies()
    .subscribe((data)=>this.movies=data)
  }


  deleteMovie(id:number){
    this.http.delete("http://127.0.0.1:8000/movie/"+id+".json").subscribe(
      res => {
        this.getmovies();
      }
    );

  }

  
  openMovieForm(){
    this.dialog.open(MovieFormComponent)
  }
}
