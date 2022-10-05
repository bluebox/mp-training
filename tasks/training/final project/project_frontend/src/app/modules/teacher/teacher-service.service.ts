import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class TeacherServiceService {

  constructor(private http:HttpClient ) { }
  getCourses():Observable <any>{

    return this.http.get("http://127.0.0.1:8000/teacherlist")

  }

  addCourse(data:any){
    return this.http.post<any>("http://127.0.0.1:8000/course", data)

  }



}
