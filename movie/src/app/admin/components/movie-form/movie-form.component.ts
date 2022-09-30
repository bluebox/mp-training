import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { MovieInterface } from 'src/app/interface/movie';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { AltermoviesComponent } from '../altermovies/altermovies.component';

@Component({
  selector: 'app-movie-form',
  template: `
    <div class="container-fluid">
    <h1>Add a new movie!</h1>
    <form [formGroup]="newMovie" (ngSubmit)="OnSubmit(newMovie.value)">
        {{newMovie.value | json}}
        <div class="form-group" style="margin-bottom:20px">
            <label>id</label>
            <input type="number" #Movie_id class="form-control" name="Movie_id" id="Movie_id" name="Movie_id" formControlName="Movie_id">
        </div>
        <div class="form-group" style="margin-bottom:20px">
            <label>Name</label>
            <input type="text" #Movie_name class="form-control" name="Movie_name" id="Movie_name" name="Movie_name" formControlName="Movie_name" ngModel>
        </div>
        <div class="form-group">
            <label>Language</label>
            <input type="text" class="form-control" formControlName="Movie_lang" name="Movie_lang" >
        </div>
        <mat-divider></mat-divider>
        <mat-label>Release Date</mat-label>
        <input name="Movie_Release_date" type="date" formControlName="Movie_Release_date" >

        <div class="form-group">
        <label>Details</label>
        <input type="text" class="form-control" name="Movie_details" formControlName="Movie_details" >
        </div>
        <div class="form-group">
            <label>Category</label>
            <input type="text" class="form-control" formControlName="Movie_category" name="Movie_category">
        </div>
        <div class="form-group">
            <label>Cast</label>
            <input type="text" class="form-control" name="Movie_cast" formControlName="Movie_cast">
        </div>
        <div class="form-group">
            <label>Reviews</label>
            <input type="text" class="form-control" name="Movie_reviews" formControlName="Movie_reviews">
        </div>
        <button type="submit"  mat-button mat-dialog-close > Submit </button>
        
    </form>

</div>
  `,
  styles: [
  ]
})
export class MovieFormComponent implements OnInit{
    public movies:MovieInterface[]=[]
  newMovie:FormGroup=new FormGroup({
    Movie_id:new FormControl(""),
    Movie_name:new FormControl(""),
    Movie_lang:new FormControl(""),
    Movie_Release_date: new FormControl(""),
    Movie_details: new FormControl(""),
    Movie_category: new FormControl(""),
    Movie_cast: new FormControl(""),
    Movie_reviews: new FormControl("")


  })

  constructor(private movie:MoviedataService,private route:Router) { }

  ngOnInit(): void {
    this.getMovies
  }
  getMovies(){
    this.movie.getMovies().subscribe((data) =>this.movies=data)
  }
  OnSubmit(data:any){
      this.movie.saveMovie(data).subscribe(data=>this.route.navigate(['admin/altermovies']))
      console.log(this.newMovie.value)
      this.getMovies()

  }

}
