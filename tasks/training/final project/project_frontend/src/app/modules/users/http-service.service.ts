import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) { }


  registerStudent(data: any){
    return this.http.post<any>('http://127.0.0.1:8000/userstudent',data)
  }
  // getUsers(){
  //   return this.http.get('http://127.0.0.1:8000/userstudent')
  // }
  registerTeacher(data: any){
    return this.http.post<any>('http://127.0.0.1:8000/userteacher',data)
  }

}
