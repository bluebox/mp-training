import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(private http:HttpClient) { }

  registerNewStudent(studentData: any): Observable<any>{
    return this.http.post('http://127.0.0.1:8000/Student/create-student',studentData);
  }

  displayAllStudent(){
    return this.http.get('http://127.0.0.1:8000/Student/display-student');
  }

  updateStudent(studentData:any, username:any){
    return this.http.post<any>('http://127.0.0.1:8000/Student/update-student/'+username,studentData);
  }

  searchStudent(studentData:any){
    return this.http.post('http://127.0.0.1:8000/Student/search-student',studentData);
  }

  deleteStudent(username:any){
    return this.http.get<any>('http://127.0.0.1:8000/Student/delete-student/'+username);
  }

  registerNewFaculty(facultyData: any): Observable<any>{
    return this.http.post('http://127.0.0.1:8000/Faculty/create-faculty',facultyData);
  }

  updateFaculty(facultyData:any, username:any){
    return this.http.post<any>('http://127.0.0.1:8000/Faculty/update-faculty/'+username,facultyData);
  }

  displayAllFaculty(){
    return this.http.get('http://127.0.0.1:8000/Faculty/display-faculty');
  }

  deleteFaculty(username:any){
    return this.http.get<any>('http://127.0.0.1:8000/Faculty/delete-faculty/'+username);
  }

  allclasses(){
    return this.http.get('http://127.0.0.1:8000/Student/all-classes');
  }

  allsubjects(){
    return this.http.get('http://127.0.0.1:8000/Faculty/all-subjects');
  }

  alldepartments(){
    return this.http.get('http://127.0.0.1:8000/Faculty/all-departments');
  }

}
