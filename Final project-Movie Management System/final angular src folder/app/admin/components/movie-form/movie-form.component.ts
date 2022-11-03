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
        // {{newMovie.value | json}}
        <div class="form-group" style="margin-bottom:20px">
            <label>id</label>
            <input type="number" #Movie_id class="form-control" name="Movie_id" id="Movie_id" name="Movie_id" formControlName="Movie_id">
            <mat-error *ngIf="newMovie.get('Movie_id')?.touched && newMovie.get('Movie_id')?.hasError('required')">Enter movie id</mat-error>

        </div>
        <div class="form-group" style="margin-bottom:20px">
            <label>Name</label>
            <input type="text" #Movie_name class="form-control" name="Movie_name" id="Movie_name" name="Movie_name" formControlName="Movie_name" ngModel>
            <mat-error *ngIf="newMovie.get('Movie_name')?.touched && newMovie.get('Movie_name')?.hasError('required')">Enter movie name</mat-error>

        </div>
        <div class="form-group">
            <label>Language</label>
            <input type="text" class="form-control" formControlName="Movie_lang" name="Movie_lang" >
            <mat-error *ngIf="newMovie.get('Movie_lang')?.touched && newMovie.get('Movie_lang')?.hasError('required')">Enter movie language</mat-error>

        </div>
        <mat-divider></mat-divider>
        <mat-label>Release Date</mat-label>
        <input name="Movie_Release_date" type="date" formControlName="Movie_Release_date" >
        <mat-error *ngIf="newMovie.get('Movie_Release_date')?.touched && newMovie.get('Movie_Release_date')?.hasError('required')">Enter movie release date</mat-error>

        <div class="form-group">
        <label>Details</label>
        <input type="text" class="form-control" name="Movie_details" formControlName="Movie_details" >
        <mat-error *ngIf="newMovie.get('Movie_details')?.touched && newMovie.get('Movie_details')?.hasError('required')">Enter movie details</mat-error>
        
        </div>
        <div class="form-group">
            <label>Category</label>
            <input type="text" class="form-control" formControlName="Movie_category" name="Movie_category">
            <mat-error *ngIf="newMovie.get('Movie_category')?.touched && newMovie.get('Movie_category')?.hasError('required')">Enter movie category</mat-error>
            
        </div>
        <div class="form-group">
            <label>Cast</label>
            <input type="text" class="form-control" name="Movie_cast" formControlName="Movie_cast">
            <mat-error *ngIf="newMovie.get('Movie_cast')?.touched && newMovie.get('Movie_cast')?.hasError('required')">Enter movie cast</mat-error>

        </div>
        <div class="form-group">
            <label>Reviews</label>
            <input type="text" class="form-control" name="Movie_reviews" formControlName="Movie_reviews">
            <mat-error *ngIf="newMovie.get('Movie_reviews')?.touched && newMovie.get('Movie_reviews')?.hasError('required')">Enter movie reviews</mat-error>
            
        </div>
        <div class="form-group">
            <label>Poster url</label>
            <input type="text" class="form-control" name="Movie_poster" formControlName="Movie_poster">
            <mat-error *ngIf="newMovie.get('Movie_poster')?.touched && newMovie.get('Movie_poster')?.hasError('required')">Enter movie reviews</mat-error>
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
    Movie_id:new FormControl("",[Validators.required,Validators.pattern('^[0-9]')]),
    Movie_name:new FormControl("",[Validators.required]),
    Movie_lang:new FormControl("",[Validators.required]),
    Movie_Release_date: new FormControl("",[Validators.required]),
    Movie_details: new FormControl("",[Validators.required]),
    Movie_category: new FormControl("",[Validators.required]),
    Movie_cast: new FormControl("",[Validators.required]),
    Movie_reviews: new FormControl("",[Validators.required]),
    Movie_poster:new FormControl("",[Validators.required])


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
