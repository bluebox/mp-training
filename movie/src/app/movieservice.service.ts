import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MovieInterface } from './interface/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieserviceService {
  public url='./assets/data/movies.json'

  constructor(private http:HttpClient) {}

    get_movies():Observable<MovieInterface[]>{
      return this.http.get<MovieInterface[]>(this.url)
   }
}

