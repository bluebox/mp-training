import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService {

  constructor(private http : HttpClient) { }
  postManufacturer(data:any){
    return this.http.post<any>("http://localhost:8000/manufacturer/",data);
  }

  getManufacturer(){
    return this.http.get<any>("http://localhost:8000/manufacturer/");
  }

  putManufacturer(data:any,id:string){
    return this.http.put<any>("http://localhost:8000/manufacturer/"+id,data);

  }

  deleteManufacturer(id:string){
    return this.http.delete<any>("http://localhost:8000/manufacturer/"+id);
  }



}
