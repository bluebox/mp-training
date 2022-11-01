import { Component, OnInit } from '@angular/core';
import { ServicesService } from 'src/app/services.service';

@Component({
  selector: 'app-managertask',
  templateUrl: './managertask.component.html',
  styleUrls: ['./managertask.component.css']
})
export class ManagertaskComponent implements OnInit {
  complaints :any
  manager :any
  managerdata:any
  employeelist:any
  text: string =''
  list: any
  page=1;
  currenttext:string=""

  assignedto:string='all'
  isassigned:string='all'

  
  constructor(private service : ServicesService) { 
    this.managerdata=sessionStorage.getItem('userdetails')
    if(this.managerdata){
      this.manager=JSON.parse(this.managerdata)
    }
    console.log();
    
    this.service.getManagerTasks(this.manager.emp_id,'',1,this.assignedto,this.isassigned).subscribe(data=>{this.complaints=data
      this.list=this.complaints.pageItems
   
      console.log(this.list)}
    )
    this.service.getEmployeelist(this.manager.emp_id).subscribe(data =>{
      this.employeelist=data
    })
    console.log(this.complaints);
     
  }
  assignComplaint(comp_id : number){
    let emp_id=document.getElementById('employee') as HTMLInputElement
    console.log(comp_id,emp_id.value);
    this.service.setComplaint(comp_id,emp_id.value).subscribe(data =>{ console.log(data)
      this.service.getManagerTasks(this.manager.emp_id,'',1,this.assignedto,this.isassigned).subscribe(data=>{this.complaints=data
        this.list=this.complaints.pageItems
     
        console.log(this.list)}
      )
    } )
    // window.location.reload()

  }


  search(){
    // this.currenttext=text
    console.log(this.text);
    
    this.service.getManagerTasks(this.manager.emp_id,this.text,1,this.assignedto,this.isassigned).subscribe(data=>{this.complaints=data
      this.list=this.complaints.pageItems
   
      console.log(this.list)}
    )

  }


  next(num:number){
if(num==1){
  console.log(num);
  
  this.page++;
  console.log(this.page);
  
}
if (num==-1){
  console.log(num);
  this.page--;
  console.log(this.page);
  
}
    this.service.getManagerTasks(this.manager.emp_id,this.currenttext,this.page,this.assignedto,this.isassigned).subscribe(data=>{this.complaints=data
      this.list=this.complaints.pageItems
      console.log(this.list)}
    )
  }

  ngOnInit(): void {
    
  }


}
