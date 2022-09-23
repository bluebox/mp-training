import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  url: string= 'http://localhost:8000/vehicle/vehicle/'

  constructor(private http: HttpClient) { }

  getUsers(){
    return this.http.get(this.url)
  }
  getUsersById(id: number){
    return this.http.get(this.url + "/"+ id )
  }

}

