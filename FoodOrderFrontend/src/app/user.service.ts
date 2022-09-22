import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url:string="https://jsonplaceholder.typicode.com/posts"

  constructor(private http:HttpClient) { }

  getUser():Observable<Student[]>{
    return this.http.get<Student[]>(this.url)
  }
  

}
