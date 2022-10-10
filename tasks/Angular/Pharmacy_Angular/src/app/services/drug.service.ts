import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class DrugService {

  constructor(private http : HttpClient) { }
  postDrug(data:any){
    return this.http.post<any>("http://localhost:8000/drug/",data);
  }

  getDrug(){
    return this.http.get<any>("http://localhost:8000/drug/");
  }

  putDrug(data:any,id:string){
    return this.http.put<any>("http://localhost:8000/drug/"+id,data);

  }

  deleteDrug(id:string){
    return this.http.delete<any>("http://localhost:8000/drug/"+id);
  }



}
