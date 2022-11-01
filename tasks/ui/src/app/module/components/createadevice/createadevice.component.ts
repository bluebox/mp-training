import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { ServicesService } from 'src/app/services.service';

@Component({
  selector: 'app-createadevice',
  templateUrl: './createadevice.component.html',
  styleUrls: ['./createadevice.component.css']
})
export class CreateadeviceComponent implements OnInit {
device : any ;
list :any
user : any
data:any
page=1
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
   
  
  this.service.getEmployeeDevices(this.user.facility_id,this.page).subscribe(data=>{ this.data=data
    this.list=this.data.pageItems
  console.log(data);

  })

    // console.log(this.list);
    
  }
 
adddevice(){
 
  console.log(this.device.value);
  this.service.adddevice(this.device.value).subscribe(data =>{ console.log(data)
 this.service.getEmployeeDevices(this.user.facility_id,this.page).subscribe(data=>{ 
  this.data=data
  this.list=this.data.pageItems
  console.log(data);
  this.device.reset()
  })
})
  
}
delete(text:number){
  this.page=0
  this.service.deletedevice(text).subscribe(data =>{console.log(data)
  this.service.getEmployeeDevices(this.user.facility_id,this.page).subscribe(data=>{ 
    this.data=data
    this.list=this.data.pageItems
    console.log(data);
    
    })
  }
  )
  console.log(text);
 
  
}
next(num:any){
 if(num=='1')this.page++;
 if(num=='-1') this.page--;
 console.log(this.page);
 
  this.service.getEmployeeDevices(this.user.facility_id,this.page).subscribe(data=>{ this.data=data
    this.list=this.data.pageItems
  console.log(data);

  })
}
  ngOnInit(): void {

    
  
  }

}
