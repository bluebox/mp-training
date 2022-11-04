import { Component } from '@angular/core';
import {UserserviceService} from './services/userservice.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bank_system_client';
  users:any;
  constructor(private userdata:UserserviceService){
    userdata.userapi().subscribe((data)=>{
      
      this.users = data
    })
    
  }

  
}
