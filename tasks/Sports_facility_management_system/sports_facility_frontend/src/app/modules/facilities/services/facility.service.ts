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
  private facilitiesurl: string = 'http://localhost:8000/facilities?page=';
  private facilityurl: string = 'http://localhost:8000/facility?fid='
  private sporturl: string = 'http://localhost:8000/sport-facility?fid=';
  private slotsurl: string = 'http://localhost:8000/GetSlotsInSportFacility/';
  private bookedslotsurl: string = 'http://127.0.0.1:8000/get-booked-slots?fsid=';
  private equipmenturl: string = 'http://localhost:8000/get-equipments?sid=';

  private facilitysporturl:string ='http://127.0.0.1:8000/get-fsid?fid='
  private ratingUrl:string='http://127.0.0.1:8000/get-ratings-facility?fid='
  private searchfacilityurl:string ='http://127.0.0.1:8000/search-facilities?q='
  private sportsurl:string ='http://127.0.0.1:8000/get-sports'

  private searchurl:string ='http://127.0.0.1:8000/get-facilities-contain-sport?q='

  private Refreshtokenurl:string ='http://127.0.0.1:8000/check-refresh-token?refresh_token='

  private searchloadmore:string ='http://127.0.0.1:8000/search-facilities?q='
  is_authenticated:boolean =false;
  is_admin:boolean = false;
  is_user:boolean = false;
  constructor( private http: HttpClient ) { 
    let token = localStorage.getItem('refresh_token');
    this.CheckRefreshToken(token).subscribe(
      (data) => {
        let res = JSON.stringify(data);
        let Parsed = JSON.parse(res);
        this.is_authenticated=true
        if (Parsed.is_admin) {
          this.is_admin = true;
          
        } else {
          this.is_user=true
        }

        
      },
      (err) => {
        this.is_authenticated=false
      }
    );
  }

  getFacilities(page:number) {

    return this.http.get(this.facilitiesurl+page);
    
  }
  getFacility(id: string): Observable<Facility> {
    return this.http.get<Facility>(this.facilityurl+id);
  }
  

  getSportsInFacility(id: string): Observable<Sports[]> {
    return this.http.get<Sports[]>(this.sporturl+String(id));
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

  searchFacility(q: string):Observable<Facility[]> {
    return this.http.get<Facility[]>(this.searchfacilityurl+q)

    
  }
  getSports() {
    return this.http.get(this.sportsurl)
  }
  getFacilitiescontainSport(q:any,sid:any,page:any) {

    return this.http.get(this.searchurl+ q+'&&sid='+sid+'&&page='+page)
  }

  CheckRefreshToken(token:any) {
    
    return this.http.get(this.Refreshtokenurl+token)

    

  }

  searchFacilityLoadmore(q:any,sid:any,x:any) {
    console.log(q,sid, x);
    return this.http.get(this.searchloadmore+ q+'&&sid='+sid+'&&x='+x)

  }
  getRatingsOfFacilities(fid:any){

    return this.http.get(this.ratingUrl+fid)
  }
}

