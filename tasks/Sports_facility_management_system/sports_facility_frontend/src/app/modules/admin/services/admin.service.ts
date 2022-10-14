import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private facilitiesurl: string = 'http://localhost:8000/facilities';
  private facilityURL: string = 'http://localhost:8000/facility';
  private addSporturl: string = 'http://localhost:8000/add-sports';
  private slotsurl: string = 'http://localhost:8000/get-slots'

  constructor(private http: HttpClient,) { }

  postFacility(formdata: any)  {
    return this.http.post(this.facilityURL,formdata)
  }

  getFacilities() {
    return this.http.get(this.facilitiesurl)
  }
  deleteFacility(fid:any){
    return this.http.delete(this.facilityURL+'?fid='+fid)
  }
  addsports(obj: any) {
    return this.http.post(this.addSporturl, obj)
  }

  getSlots(){
    return this.http.get(this.slotsurl)
  }
}
