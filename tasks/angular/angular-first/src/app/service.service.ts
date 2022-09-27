import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  url : string = 'http://localhost:8000/'
  constructor(private http: HttpClient) { }

  getConfig() {
    return this.http.get(this.url);
  }

  freelancerRegistration(data: any ){
    console.log(data);
    return this.http.post(this.url+'freelancer',data)
  }
  FreelancerLogin(data : string){
    console.log(data);
    
    return this.http.get(this.url+"freelancer/"+data+'/')
  }

  ClientRegistration(data:any){
    console.log(data);
    
    return this.http.post(this.url+'client',data)
  }

  ClientLogin(data : string){
    return this.http.get(this.url+'client/'+data + '/')
  }
}
