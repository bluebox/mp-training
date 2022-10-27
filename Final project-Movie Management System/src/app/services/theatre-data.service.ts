import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MovieInterface } from '../interface/movie';
import { TheatreInterface } from '../interface/theatre';

@Injectable({
  providedIn: 'root'
})
export class TheatreDataService {
  public url:string="http://127.0.0.1:8000/theatre/"

  constructor(private http:HttpClient) { }
  getTheatres(){
    return this.http.get<TheatreInterface[]>(this.url)
  }
  getSingleTheatre(id:number){
    return this.http.get<TheatreInterface>(this.url+id+'.json')
  }
  postTheatre(data:TheatreInterface){
    return this.http.post(this.url,data)
  }
  getOwnertheatres(owner:string){
    return this.http.get<TheatreInterface[]>("http://127.0.0.1:8000/ownerstheatre/"+owner)
  }
  search(id:number,date:Date,a:string){
    return this.http.get("http://127.0.0.1:8000/searcht/"+id+"/"+date+"/"+a)
  }
}
