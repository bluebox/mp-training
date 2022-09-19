import { Injectable } from '@angular/core';
import { newInterface } from './interface';

@Injectable({
  providedIn: 'root'
})

// creating a service for new interface
export class NewService {
  firstInterface:newInterface = {
    name:"sairam",
    age:21,
  };
  constructor() { 
    
  }
}
