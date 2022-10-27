import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';
import { MovieInterface } from '../interface/movie';



@Injectable({
  providedIn: 'root'
})
export class MoviedataService {
  public url:string="http://127.0.0.1:8000/movie/"

  constructor(private http:HttpClient ) {}
    getMovies(){
      return this.http.get<MovieInterface[]>(this.url)
    }
    getSingleMovie(id: number){
      return this.http.get<MovieInterface>("http://127.0.0.1:8000/movie/"+id+'.json')}

    // saveUser(data:any){
    //   return this.http.post(this.url,data)
    // }
    deleteMovie(id:number){
      this.http.delete("http://127.0.0.1:8000/movie/"+id+".json")
    }
    saveMovie(data:any){
      return this.http.post(this.url,data)
    }

    getMovieByLang(lang:string){
      return this.http.get<MovieInterface[]>("http://127.0.0.1:8000/lang/"+lang)
    }
    searchmovie(a:string){
      return this.http.get<MovieInterface[]>("http://127.0.0.1:8000/search/"+a)
    }
    getTop(){
      return this.http.get<MovieInterface[]>("http://127.0.0.1:8000/top")
    }
}
