import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Employee } from '../Facility';
import { ServicesService } from '../services.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  facilities :any 
  devices : any 
  user:Employee | undefined;
  userid :any
  complaintform :FormGroup | undefined;
  successmsg: any 
  constructor(private service:ServicesService) { 

  
    var userdata=sessionStorage.getItem("userdetails")
    console.log(userdata);
    console.log(typeof(userdata));
    if (userdata){
     this.user = JSON.parse(userdata);
     let pic=document.getElementById('pic') as HTMLImageElement
   if(this.user)  this.userid=this.user.emp_id;
  //  this.service.getEmployeeDevices().subscribe(data =>{ this.devices=data,
  // console.log(data);
  // })
}

   
  }



  ngOnInit(): void {
  }

}
