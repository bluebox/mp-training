import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-raise-complaint',
  templateUrl: './raise-complaint.component.html',
  styleUrls: ['./raise-complaint.component.css']
})
export class RaiseComplaintComponent implements OnInit {
  complaintform: any;
  userid: any;
  facilities: any;
  devices: any;
  successmsg: string='';
  id : number = 3;
  user: any;
  msg:string=''
  data:any
  

  constructor(private service :ServicesService) { 
    var userdata=sessionStorage.getItem("userdetails")
    // let user= JSON.parse(userdata)
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

    this.complaintform= new FormGroup({
      facility_id : new FormControl('',Validators.required),
      emp_id :  new FormControl(this.userid),
      device_id: new FormControl('',Validators.required),
      comp_desc :new FormControl()

    })
    this.service.getfacilities().subscribe(data => {this.facilities=data,console.log(data);
    })
  }

  ngOnInit(): void {
  }
  clear(){
    this.successmsg=''
  }
  submitcomplaint(){
 
    // console.log(this.complaintform.value);
    this.msg="please enter all the requried details"
  //  if(this.complaintform.Valid){
    this.msg=''
   
    this.service.postComplaint(this.complaintform.value).subscribe(data=>console.log(data)
    )
      this.successmsg=" a complaint has been raised "
    // }
    this.complaintform.reset()
  } 
    
  getDevices(event:any ){
    console.log(event);
    this.service.getEmployeeDevices(event,1).subscribe(data =>{ this.data=data,
      this.devices=this.data.pageItems
      console.log(data);
    this.clear()

    })

    
  }
  }


