import { HttpClient, HttpClientModule, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
interface user {
  name: string;
  email: string;
  phonenumber: number
}
@Injectable({
  providedIn: 'root'
})

export class UsersService {


  constructor(private http: HttpClient) { }
  addUser(body: any) {
    const header2 = new HttpHeaders({
      'authenticationToken': 'testing123455465'
    });
    const params1 = new HttpParams()
      .set('userRole', 'admin');
     
    return this.http.post('https://jsonplaceholder.typicode.com/posts', body, { headers: header2,params:params1 })
  }
  getUsers<user>() {
    const header1 = new HttpHeaders({
      'content-type': 'application/json',
      'authenticationToken': '123455465'
    })
    const params1 = new HttpParams()
      .set('pageNum', '10')
      .set('pageSize', '100')
    return this.http.get<user>('https://jsonplaceholder.typicode.com/users', { headers: header1, params: params1 })
  }
}
