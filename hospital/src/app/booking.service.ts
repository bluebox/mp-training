import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  docDetails:any;
  slotDetails: any;
  userDetails: any;
  constructor() { }
  setDocData(data:any){
    this.docDetails=data


  }
  setSlotData(data:any){
    this.slotDetails=data


  }

  setUserData(data:any){
    this.userDetails=data


  }
  getUserDetails(){
    console.log(this.userDetails)
    return this.userDetails
  }
  getDocDetails(){
    console.log(this.docDetails)
    return this.docDetails
  }
  getSlotDetails(){
    console.log(this.slotDetails)
    return this.slotDetails
  }
}
