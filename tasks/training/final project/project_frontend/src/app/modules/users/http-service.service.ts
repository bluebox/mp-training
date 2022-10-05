import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';

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

  handleError(err:any):any{
    // console.log(err);  
    let errorMessage =""
    if(err.error instanceof ErrorEvent){
      errorMessage = `Error: ${err.message}`;
    }else{
      errorMessage = `Error Code : ${err.status}\nMessage: ${err.message}`;
    }
    alert(errorMessage);
    return throwError(errorMessage)
}

  loginUser(data:any): Observable<any>{
    return this.http.post<any>('http://127.0.0.1:8000/userlogin',data).pipe(
      retry(3),
      catchError(this.handleError)
    )
  }

}
