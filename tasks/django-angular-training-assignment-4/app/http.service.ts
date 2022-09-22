import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  subscribe(arg0: (data: any) => void) {
    throw new Error('Method not implemented.');
  }

  url: string = "https://jsonplaceholder.typicode.com/posts/";
  constructor(private http: HttpClient) { }

  findWeather(id: string) {
    return this.http.get(this.url + id);
  }

}