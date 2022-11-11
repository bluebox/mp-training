import { Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HallInterface } from '../interface/hall';

@Injectable({
  providedIn: 'root'
})
export class HallDataService {
  public date!:Date
  public slot!:string
  public url:string= "http://127.0.0.1:8000/Hall/"

  constructor(private http:HttpClient) { }
  postHallData(data:any){
    return this.http.post(this.url,data)
  }
  getHallData(){
    return this.http.get<HallInterface[]>(this.url)
  }
  getSingleHall(id: number){
    return this.http.get<HallInterface>("http://127.0.0.1:8000/Hall/"+id+'.json')}
  updateHall(id:number,body:any){
    return this.http.put("http://127.0.0.1:8000/Hall/"+id+'.json',body)
  }
  getFilteredData(date:any){
    return this.http.get("http://127.0.0.1:8000/date/"+date)
  }
  getMovieHalls(id:number){
    return this.http.get("http://127.0.0.1:8000/screen/"+id)
  }
  DeductSeat(id:number,date:Date,body:any){
    return this.http.put("http://127.0.0.1:8000/deduct/"+id+"/"+date+'/',body)
  }
  SingleHall(id:number,date:Date,time:string){
    return this.http.get("http://127.0.0.1:8000/singlehall/"+id+'/'+date+'/'+time)
  }
  setDate(date:Date){
    this.date=date
  }
  getDate(){
    return this.date
  }
  getTheatreList(id:number,date:Date){
    return this.http.get("http://127.0.0.1:8000/theatrelist/"+id+'/'+date)
  }
  getHallList(id:number){
    return this.http.get<HallInterface[]>("http://127.0.0.1:8000/halllist/"+id)
  }
  postSchedule(data:any){
    return this.http.post("http://127.0.0.1:8000/Date/",data)
  }
  setTime(time:Time){
    this.slot=time.toString()
  }
  getTime(){
    return this.slot
  }
}
