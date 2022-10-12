import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {
  total_scores:number[]=[]

  constructor(private router:Router, private http:HttpClient) { }

  // getQues():Observable <any>{
  //   // let queryParams = new HttpParams();
  //   // queryParams = queryParams.append("course",1);   
  //   return this.http.get("http://127.0.0.1:8000/attemptexam")
  // }
  getCourses():Observable<any>{

    return this.http.get("http://127.0.0.1:8000/courselist")
  }
  sendCourse(data:any){
    return this.http.post("http://127.0.0.1:8000/startexam",data)
  }

  getQuestionWdCourse():Observable<any>{
    return this.http.get("http://127.0.0.1:8000/startexam")
  }

  sendAttemts(data:any){
    return this.http.post("http://127.0.0.1:8000/checkmarks",data)
  }

 
  getQuestions(){
    return this.http.get("http://127.0.0.1:8000/questionregister")
  }



}
