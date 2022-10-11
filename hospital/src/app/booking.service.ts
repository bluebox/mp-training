import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
docDetails:any;
  constructor() { }
  setDocData(data:any){
    this.docDetails=data


  }
  getDocDetails(){
    console.log(this.docDetails)
    return this.docDetails
  }
}
