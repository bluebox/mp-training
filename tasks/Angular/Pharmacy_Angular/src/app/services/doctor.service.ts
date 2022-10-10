import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http : HttpClient) { }
  postDoctor(data:any){
    return this.http.post<any>("http://localhost:8000/doctor/",data);
  }

  getDoctor(){
    return this.http.get<any>("http://localhost:8000/doctor/");
  }

  putDoctor(data:any,id:string){
    return this.http.put<any>("http://localhost:8000/doctor/"+id,data);

  }

  deleteDoctor(id:string){
    return this.http.delete<any>("http://localhost:8000/doctor/"+id);
  }



}
