import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Facility } from 'src/interfaces/facility';
import { Sports } from 'src/interfaces/sport';
import {Slots} from 'src/interfaces/slot';

@Injectable(
  {
  providedIn: 'root'
}
)
export class FacilityService {
  private bookingurl: string = 'http://localhost:8000/bookingform';
  private facilityurl: string = 'http://localhost:8000/facilities';
  private sporturl: string = 'http://localhost:8000/sf';
  private slotsurl: string = 'http://localhost:8000/slots/';
  private bookedslotsurl: string = 'http://127.0.0.1:8000/get-booked-slots?fsid=';
  private equipmenturl: string = 'http://localhost:8000/get-equipments?sid=';

  private facilitysporturl:string ='http://127.0.0.1:8000/get-fsid?fid='
  

  constructor( private http: HttpClient ) { }

  getFacilities(): Observable<Facility[]> {

    return this.http.get<Facility[]>(this.facilityurl);
    
  }
  getFacility(id: string): Observable<Facility> {
    return this.http.get<Facility>(this.facilityurl+"/"+id);
  }
  

  getSportsInFacility(id: string): Observable<Sports[]> {
    return this.http.get<Sports[]>(this.sporturl+"/"+String(id));
  }

  getSlotsInSportFacility(fid: string,sid: number): Observable<Slots[]> {
    return this.http.get<Slots[]>(this.slotsurl+fid+"/"+sid)
  }

  postBookingData(data: any){
    console.log(this.bookingurl);
    
    return this.http.post(this.bookingurl,data)
  }



  getBookedSlots(fsid:number,date:string): Observable<Slots[]> {
    return this.http.get<Slots[]>(this.bookedslotsurl+fsid+"&date="+date)
  }

  getEquipments(sid:number): Observable<Slots[]> {
    return this.http.get<Slots[]>(this.equipmenturl+sid)
  }


  getFacilitySportId(fid:string,sid:number) {
    return this.http.get(this.facilitysporturl+fid+"&sid="+sid)
  }



}