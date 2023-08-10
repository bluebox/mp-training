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
  registerUser(data:any):Observable<any>
  {
    console.log('user reg')
    return this.http.post<any>(this.url+'users/register/',data,{headers: this.httpHeaders});
  }
  login(data:any) {
    return this.http.post<any>(this.url+'users/login/',data,{withCredentials:true,headers: this.httpHeaders});
        // .pipe(map(data => {
        //     // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
        //     localStorage.setItem(data);
        //     this.userSubject.next(user);
        //     return user;
        // }));
}
logout(){
  return this.http.post<any>(this.url+'users/logout/',"",{withCredentials:true,headers: this.httpHeaders});

}
  getAllPatient():Observable<any>
  {
    console.log('patient list get')
    return this.http.get(this.url+'users/patient/',{headers: this.httpHeaders});
  }
  getuser():Observable<any>
  {
    console.log('patient list get')
    return this.http.get(this.url+'users/getuser/',{withCredentials:true,headers: this.httpHeaders});
  }

  getAllDoctor():Observable<any>
  {
    console.log('doctor list get')
    return this.http.get(this.url+'users/doctor/',{headers: this.httpHeaders});
  }
  getAllBills():Observable<any>
  {
    console.log('bill list get')
    return this.http.get(this.url+'users/bill/',{headers: this.httpHeaders});
  }
  getAllAppointment():Observable<any>
  {
    console.log('doctors apointment list get')
    return this.http.get(this.url+'users/apointmentdetails/',{headers: this.httpHeaders});
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
  getAllSlot(id:any,date:any):Observable<any>
  {
    console.log('slot list get')
    return this.http.get(this.url+'users/slotfilter/'+id+'/'+date,{headers: this.httpHeaders});
  }
  getASlot(id: any):Observable<any>
  {
    console.log('diagnosis list get')
    return this.http.get(`${this.url}users/slot/${id}`,{headers: this.httpHeaders});
  }
  getADoctor(email: any):Observable<any>
  {
    console.log('single doc details get')
    return this.http.get(`${this.url}users/getdoctor/${email}/`,{headers: this.httpHeaders});
  }
  getAPatient(email: any):Observable<any>
  {
    console.log('one patient')
    return this.http.get(this.url+'users/getpatient/'+email+'/',{headers: this.httpHeaders});
  }
  getAApointment(id: any):Observable<any>
  {
    console.log('one patient')
    return this.http.get('${this.url}appointment/appointment/${id}',{headers: this.httpHeaders});
  }

  register_patient(data: any) {
      return this.http.post(this.url+'users/patient/', data,{headers: this.httpHeaders});
  }
  register_doctor(data: any) {
    return this.http.post(this.url+'users/doctor/', data,{headers: this.httpHeaders});
}
register_staff(data: any) {
  return this.http.post(this.url+'users/staff/', data,{headers: this.httpHeaders});
}
register_appointment(data: any) {
  return this.http.post(this.url+'users/appointment/', data,{headers: this.httpHeaders});
}
register_bill(data: any) {
  return this.http.post(this.url+'bill/bill/', data,{headers: this.httpHeaders});
}
register_diagnosis(data: any) {
  return this.http.post(this.url+'diagnosis/diagnosis/', data,{headers: this.httpHeaders});
}
register_slot(data: any) {
  return this.http.post(this.url+'users/DatewiseSlot/', data,{headers: this.httpHeaders});
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

  //   findByIdDoctor(id: any) {
  //     return this.http.get(`${baseUrl}?id=${id}`);
  //   }
  // }

}
