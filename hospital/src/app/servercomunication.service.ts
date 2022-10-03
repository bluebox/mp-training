import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Patient } from './interfaces/patients';

@Injectable({
  providedIn: 'root'
})
export class ServercomunicationService {

  url="http://127.0.0.1:8000/patient/dont";
  httpHeaders= new HttpHeaders({'Content-Type':'application/json'});

  constructor( private http: HttpClient) {}

  getAllPatient():Observable<any>
  {
    console.log('hi')
    return this.http.get(this.url+'/patient/',{headers: this.httpHeaders});
  }
  // getAPatient(id: any):Observable<any>
  // {
  //   console.log('one patient')
  //   return this.http.get('${url}/${id}',{headers: this.httpHeaders});
  // }

  register_patient(data: any) {
      return this.http.post(this.url+'/patient/', data,{headers: this.httpHeaders});
  }

  //   update(id: any, data: any) {
  //     return this.http.put(`${baseUrl}/${id}`, data);
  //   }

  //   delete(id: any) {
  //     return this.http.delete(`${baseUrl}/${id}`);
  //   }

  //   deleteAll() {
  //     return this.http.delete(baseUrl);
  //   }

  //   findByTitle(title: any) {
  //     return this.http.get(`${baseUrl}?title=${title}`);
  //   }
  // }

}
