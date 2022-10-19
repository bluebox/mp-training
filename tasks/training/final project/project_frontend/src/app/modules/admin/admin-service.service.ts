import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private router:Router, private http:HttpClient) { }

  getStudents():Observable<any>{
    return this.http.get("http://127.0.0.1:8000/adminstudentdisplay")
  }
}
