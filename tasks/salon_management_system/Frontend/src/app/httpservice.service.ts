import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class HttpserviceService {
  ClientRegistration(value: any) {
    throw new Error('Method not implemented.');
  }

  constructor(private http : HttpClient) {  }

  getBranch(){
    return this.http.get('http://127.0.0.1:8000/branch/')
  }

  getServices(){
    return this.http.get(' http://127.0.0.1:8000/services')
  }

  
  // registerStudent(data: any){
  //   return this.http.post<any>('http://127.0.0.1:8000/userstudent',data)
  // }
  clientRegister(data : unknown){
    return this.http.post<unknown>('http://127.0.0.1:8000/ClientRegistration',data)
  }
}
