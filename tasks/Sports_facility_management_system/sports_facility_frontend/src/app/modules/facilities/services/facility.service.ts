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

  private facilityurl: string = 'http://localhost:8000/facilities';
  private sporturl: string = 'http://localhost:8000/sf';
  private slotsurl: string = 'http://localhost:8000/slots/';

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

  getSlotsInSportFacility(fid: string,sid: string): Observable<Slots[]> {
    return this.http.get<Slots[]>(this.slotsurl+fid+"/"+sid)
  }
}