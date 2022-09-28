import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class HttpserviceService {

  constructor(private http : HttpClient) {  }

  getBranch(){
    return this.http.get('http://127.0.0.1:8000/branch/')
  }

  getServices(){
    return this.http.get(' http://127.0.0.1:8000/services')
  }
}
