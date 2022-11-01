import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css']
})
export class AdminpageComponent implements OnInit {
  user: any;
  device: FormGroup<{ device_id: any; facility_id: any; device_name: any; }>;
  router: any;
  list: any;

  constructor( private service : ServicesService ) {
      let userdata = sessionStorage.getItem("userdetails")
      if (userdata) {
        this.user = JSON.parse(userdata)
      }
      this.device= new FormGroup({
        device_id : new FormControl(),
        facility_id:new FormControl(this.user.facility_id),
        device_name: new FormControl(),
  
      })
      this.service.getEmployeeDevices(2,1).subscribe(data=>{ this.list=data
        console.log(data);
      
        })
   }

  ngOnInit(): void {
  }
  logout(){
    
    sessionStorage.removeItem('userdetails');
    this.router.navigate(['../login'])
  }

}
