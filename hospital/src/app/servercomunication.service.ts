import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Patient } from './interfaces/patients';

@Injectable({
  providedIn: 'root'
})
export class ServercomunicationService {

  url="http://127.0.0.1:8000/";
  httpHeaders= new HttpHeaders({'Content-Type':'application/json'});

  constructor( private http: HttpClient) {}

  getAllPatient():Observable<any>
  {
    console.log('patient list get')
    return this.http.get(this.url+'patient/dont/patient/',{headers: this.httpHeaders});
  }
  getAllDoctor():Observable<any>
  {
    console.log('doctor list get')
    return this.http.get(this.url+'doctor/doctor/',{headers: this.httpHeaders});
  }
  getAllBills():Observable<any>
  {
    console.log('bill list get')
    return this.http.get(this.url+'/bill/bill/',{headers: this.httpHeaders});
  }
  getAllAppointment():Observable<any>
  {
    console.log('doctors apointment list get')
    return this.http.get(this.url+'appointment/appointment',{headers: this.httpHeaders});
  }
  getAllSalary():Observable<any>
  {
    console.log('salary list get')
    return this.http.get(this.url+'salary/salary/',{headers: this.httpHeaders});
  }
  getAllDiagnosis():Observable<any>
  {
    console.log('diagnosis list get')
    return this.http.get(this.url+'diagnosis/diagnosis/',{headers: this.httpHeaders});
  }
  getAllSlot():Observable<any>
  {
    console.log('diagnosis list get')
    return this.http.get(this.url+'slot/slot/',{headers: this.httpHeaders});
  }
  getASlot(id: any):Observable<any>
  {
    console.log('diagnosis list get')
    return this.http.get(`${this.url}slot/slot/${id}`,{headers: this.httpHeaders});
  }
  getAPatient(id: any):Observable<any>
  {
    console.log('one patient')
    return this.http.get('${this.url}patient/dont/patient/${id}',{headers: this.httpHeaders});
  }
  getAApointment(id: any):Observable<any>
  {
    console.log('one patient')
    return this.http.get('${this.url}appointment/appointment/${id}',{headers: this.httpHeaders});
  }

  register_patient(data: any) {
      return this.http.post(this.url+'patient/dont/patient/', data,{headers: this.httpHeaders});
  }
  register_doctor(data: any) {
    return this.http.post(this.url+'doctor/doctor/', data,{headers: this.httpHeaders});
}
register_staff(data: any) {
  return this.http.post(this.url+'staff/staff/', data,{headers: this.httpHeaders});
}
register_appointment(data: any) {
  return this.http.post(this.url+'appointment/appointment/', data,{headers: this.httpHeaders});
}
register_bill(data: any) {
  return this.http.post(this.url+'bill/bill/', data,{headers: this.httpHeaders});
}
register_diagnosis(data: any) {
  return this.http.post(this.url+'diagnosis/diagnosis/', data,{headers: this.httpHeaders});
}
register_slot(data: any) {
  return this.http.post(this.url+'slot/slot/', data,{headers: this.httpHeaders});
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
