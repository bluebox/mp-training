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
  
  constructor(private service : ServicesService) { 
    this.managerdata=sessionStorage.getItem('userdetails')
    if(this.managerdata){
      this.manager=JSON.parse(this.managerdata)
    }
    this.service.getManagerTasks(this.manager.emp_id).subscribe(data=>{this.complaints=data,console.log(data)}
    )
    this.service.getEmployeelist(this.manager.emp_id).subscribe(data =>{
      this.employeelist=data
    })
    console.log(this.complaints);
     
  }
  assignComplaint(comp_id : number){
    let emp_id=document.getElementById('employee') as HTMLInputElement
    // let ele=document.getElementsByName('employee') 
    console.log(comp_id,emp_id.value);
    this.service.setComplaint(comp_id,emp_id.value).subscribe(data => console.log(data)
    )
    window.location.reload()

    

  }

  ngOnInit(): void {
    
  }


}
