import { Component, OnInit } from '@angular/core';
import { Task } from 'src/app/model/task';
import { CrudService } from 'src/app/service/crud.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  taskObj : Task = new Task()
  taskArr : Task[]=[];
  addTaskValue : string = " ";
  editTaskValue: string=" ";
  edittask_name:string=" ";
  constructor(private crudservice:CrudService) { }

  ngOnInit(): void {
    this.taskObj=new Task();
    this.taskArr=[];
    this.getalltask();
  }

  getalltask(){
    this.crudservice.getAlltask().subscribe(res=>{
      this.taskArr=res;
    },err=>{
      alert(err);
    }
    )
  }
  addTask(){
    this.taskObj.task_name=this.addTaskValue;
    this.crudservice.addTask(this.taskObj).subscribe(res=>{
      this.ngOnInit();
    },err=>{
      alert(err);
    }
    )
  }
  deleteTask(etask:Task){
    this.crudservice.deleteTask(etask).subscribe(res=>{
      this.ngOnInit();
    },err=>{
      alert(err);
    }
    )
  }
  edittask(){
    // this.taskObj.
    this.taskObj.task_name=this.editTaskValue;
    this.crudservice.editTask(this.taskObj).subscribe(res=>{
      this.ngOnInit();
      alert("updated successfully")
  
    },err=>{
      alert(err);
    }
    )
  }


  edit(etask:Task){
      this.taskObj=etask;


  }

}
