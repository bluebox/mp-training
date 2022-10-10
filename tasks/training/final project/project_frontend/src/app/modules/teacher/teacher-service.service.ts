import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class TeacherServiceService {

  constructor(private router:Router, private http:HttpClient ) { }
  getTeachers():Observable <any>{

    return this.http.get("http://127.0.0.1:8000/teacherlist")

  }

  getCourses():Observable<any>{

    return this.http.get("http://127.0.0.1:8000/courselist")
  }

  getQuestions(){
    return this.http.get("http://127.0.0.1:8000/questionregister")
  }
  getRequiredQuestions(){
    return this.http.get("http://127.0.0.1:8000/displayquestion")
  }


  // addCourse(data:any){
  //   return this.http.post<any>("http://127.0.0.1:8000/courseregister", data)

  // }

  AddCourse(data:any){
    return this.http.post<any>("http://127.0.0.1:8000/addcourse",data)
  }

  addQuestion(data:any){
    return this.http.post<any>("http://127.0.0.1:8000/questionregister",data)
  }
  deleteCourses(data:any){
    return this.http.post<any>("http://127.0.0.1:8000/courselist/delete",data)
  }
  deleteQuestions(data:any){
    return this.http.post<any>("http://127.0.0.1:8000/question/delete",data)
  }




}
