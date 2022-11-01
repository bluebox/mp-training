import { Component, Inject, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { NgModel } from '@angular/forms';


export interface DialogData {
  animal: string;
  name: string;
}
@Component({
  selector: 'app-employeetasks',
  templateUrl: './employeetasks.component.html',
  styleUrls: ['./employeetasks.component.css']
})
export class EmployeetasksComponent implements OnInit {
  tasks:any 
  device : any 
  status : any
  list: any;
  temp:any
  currentid:any
  text:string =""
  page=1
  isCompleted:string=''
  constructor(private service: ServicesService,public dialog: MatDialog) { 
    this.service.getTasks().subscribe(data=> {this.tasks=data;
      this.temp=this.tasks
      this.list=this.temp
      this.search()

      })
  }
  search(){
    
   this.service.getComplaintsserarch(this.text,this.isCompleted).subscribe((data) => {this.temp=data
  this.list=this.temp})
    
    
  }
  iscompleted(text: string, iscompleted: any) {
    throw new Error('Method not implemented.');
  }
  filter(event:any ){
    

  }
setid(id:number){
  this.currentid=id;
}
  updatestatus(id :number,status :string ){
    console.log(this.currentid);
    
    console.log(status);
    this.service.updatestatus(this.currentid,status).subscribe(data =>{ console.log(data)
      this.service.getTasks().subscribe(data=> {this.tasks=data;
        this.temp=this.tasks
        this.list=this.temp
          console.log(this.tasks);
        this.search()
  
        })
    })
    console.log(id);
    
  }
  setdeviceid(device_id :number){
    console.log(device_id); 
   }


  ngOnInit(): void {
  }

}
