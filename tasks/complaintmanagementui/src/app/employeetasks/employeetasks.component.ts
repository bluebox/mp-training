import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-employeetasks',
  templateUrl: './employeetasks.component.html',
  styleUrls: ['./employeetasks.component.css']
})
export class EmployeetasksComponent implements OnInit {
  tasks:any 
  device : any 
  constructor(private service: ServicesService) { 
    this.service.getTasks().subscribe(data=> {this.tasks=data;
      this.tasks.forEach((task: any,device_name: any )=>{
        task=this.edittask(task,device_name);
        console.log(this.tasks);
      })
      
    })



    
  }
  edittask(task :any ,ele : any ){
return task+ele
  }
  
  updatestatus(id :number){
    this.service.updatestatus(id).subscribe(data => console.log(data))
    console.log(id);
    // window.location.reload()
  }
  getDevicename(device_id : number){
    this.service.getDeviceName(device_id).subscribe(data =>this.device=data )
    console.log(this.device);
    

  }
  setdeviceid(device_id :number){
    console.log(device_id);
    
    // sessionStorage.setItem('device_id', JSON.stringify(device_id))
    // console.log(device_id);
    
   }


  ngOnInit(): void {
  }

}
