import { Inject, Injectable } from '@angular/core';
import { emp } from '../component';
import{ environment } from '../../../environments/environment';
import { AppConfig } from '../../AppConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from 'src/app/AppConfig/appconfig.service';

@Injectable({
  providedIn: 'root'
})
export class Component1Service {
  emplist:emp[]=[
    {
      id:102,
      name:'shyam',
      age:22,
      address:'Hyderabad'
    },
    {
      id:103,
      name:'rajesh',
      age:21,
      address:'Nalgonda'
    },
    {
      id:104,
      name:'harish',
      age:23,
      address:'beemili'
    },
    {
      id:105,
      name:'mahesh',
      age:20,
      address:'vizag'
    },
    {
      id:106,
      name:'ganesh',
      age:27,
      address:'kurnool'
    },

  ]

  constructor(@Inject(APP_SERVICE_CONFIG) private config: AppConfig){ 
    console.log("hello bro",this.config.apiEndpoint);
    console.log('this is initializer')
  }
  getEmpList(){
    return this.emplist;
  }
}
  