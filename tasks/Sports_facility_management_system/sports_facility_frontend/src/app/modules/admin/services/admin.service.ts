import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private facilitiesurl: string = 'http://localhost:8000/facilities';
  private facilityURL: string = 'http://localhost:8000/facility';
  private Sporturl: string = 'http://localhost:8000/sport-facility?fid=';
  private slotsurl: string = 'http://localhost:8000/get-slots';
  private adminLogin: string = 'http://localhost:8000/admin-login';
  // private deletesport: string = 'http://localhost:8000/delete-sports?fid=';
  constructor(private http: HttpClient) {}

  postFacility(formdata: any) {
    return this.http.post(this.facilityURL, formdata);
  }

  getFacilities() {
    return this.http.get(this.facilitiesurl);
  }
  deleteFacility(fid: any) {
    return this.http.delete(this.facilityURL + '?fid=' + fid);
  }
  addsports(obj: any) {
    return this.http.post(this.Sporturl, obj);
  }

  getSlots() {
    return this.http.get(this.slotsurl);
  }

  adminlogin(obj: any) {
    return this.http.post(this.adminLogin, obj);
  }
  deleteSport(fid: any, sid: any) {
    return this.http.delete(this.Sporturl+ fid + '&&sid=' + sid);
  }
}
