import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DistributorService {

  constructor(private http:HttpClient) { }
  
  postDistributor(data:any){
    return this.http.post<any>("http://localhost:8000/distributor/",data);
  }

  getDistributor(){
    return this.http.get<any>("http://localhost:8000/distributor/");
  }

  getADist(data: string){
    return this.http.get<any>("http://localhost:8000/distributorget/"+data);
  }
  putDistributor(data:any,id:string){
    return this.http.put<any>("http://localhost:8000/distributor/"+id,data);

  }

  deleteDistributor(id:string){
    return this.http.delete<any>("http://localhost:8000/distributor/"+id);
  }



}
