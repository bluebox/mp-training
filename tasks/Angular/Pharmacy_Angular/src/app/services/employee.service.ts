import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http : HttpClient) { }
  postEmployee(data:any){
    return this.http.post<any>("http://localhost:8000/employee/",data);
  }

  getEmployee(){
    return this.http.get<any>("http://localhost:8000/employee/");
  }

  putEmployee(data:any,id:string){
    return this.http.put<any>("http://localhost:8000/employee/"+id,data);

  }

  deleteEmployee(id:string){
    return this.http.delete<any>("http://localhost:8000/employee/"+id);
  }



}