import { Injectable, NgModule } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

@NgModule()
export class DataServiceService {
  arr:string[]=[];
  data:any;
  constructor( private http:HttpClient){
   
     this.http.get('127.0.0.1:8000/wish').subscribe()
  }

  ngOnInit(){

  }

  getData():any{
    this.arr=['1','2','3','4','5']
    return this.data
  }
}
