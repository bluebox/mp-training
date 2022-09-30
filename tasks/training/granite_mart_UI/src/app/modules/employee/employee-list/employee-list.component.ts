import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  roles:any
  employee_list:any
  constructor(private service:DataServiceService,private router:Router) { 
      this.service.getRoles().subscribe(data=>this.roles=data)
  }

  ngOnInit(): void {
    this.service.getEmployees().subscribe(data=>{this.employee_list=data      
  })
 
}
  updateEmployee(employee_id:any){
     this.router.navigate(['employeeEdit',employee_id])
  }

  deleteEmployee(employee_id:any){
    let confirm:any= window.confirm("are you sure to delete?")
    if(confirm){
    this.service.deleteEmployee(employee_id).subscribe(data=>
      {alert(employee_id+" is deleted");location.reload();this.router.navigate(["employeesList"])
    })}
  }

  addEmployee(){
    this.router.navigate(['employeeRegistration'])
 }
}
