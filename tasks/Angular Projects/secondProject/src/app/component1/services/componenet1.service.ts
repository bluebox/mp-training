import { Injectable } from '@angular/core';
import {Roomlist} from '../component1';
@Injectable({
  providedIn: 'root'
})
export class Componenet1Service {

  constructor() {
    console.log("room is initialized")
   }


    roomlist:Roomlist[]=[
      {
      roomNumber:102,
      roomType:'dwdd',
      amenities:'kjnnkfg',
      price:500,
      photos:'',
      checkinTime:new Date('12-Nov-2022'),
      chectOutTime:new Date('22-Nov-2022'),
  
    },
    {
      roomNumber:103,
      roomType:'dwdvmvd',
      amenities:'kjlmvmlnnkfg',
      price:5020,
      photos:'',
      checkinTime:new Date('12-Nov-2012'),
      chectOutTime:new Date('22-Nov-2012'),
  
    },
    {
      roomNumber:104,
      roomType:'dwddff',
      amenities:'kjnggnkfg',
      price:5700,
      photos:'',
      checkinTime:new Date('02-Nov-2022'),
      chectOutTime:new Date('22-Nov-2022'),
  
    }]
    getComponent1(){
      return this.roomlist;
    }
  }

