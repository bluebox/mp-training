import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private facilityurl: string = 'http://localhost:8000/facilities';
  constructor(private http: HttpClient,) { }

  postFacility(formdata: any)  {
    return this.http.post(this.facilityurl,formdata)
  }
}
