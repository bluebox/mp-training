import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {

  employeeUpdate = new FormGroup({
    employee_id: new FormControl('',Validators.required),
    employee_name: new FormControl('',Validators.required),
    phone: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required),
    doj: new FormControl('',Validators.required),
    role: new FormControl('',Validators.required),
    salary: new FormControl('',Validators.required),
    address: new FormControl('',Validators.required)

  });

employee_id:any=''
roles:any
employee_data:any
constructor(public service:DataServiceService,private router:Router,private aroute:ActivatedRoute) { 
      this.service.getRoles().subscribe(data=>
        {this.roles=data})
      this.aroute.params.subscribe(data=>{this.employee_id=data['employee_id']})
      this.service.getEmployee(this.employee_id).subscribe(data=>{
        this.employee_data=data
        console.log(this.employee_data)
        this.employeeUpdate.setValue({"employee_id": this.employee_id,"employee_name":this.employee_data.employee_name,"role":this.employee_data.role_id
        ,"phone":this.employee_data.phone,"email":this.employee_data.email,"doj":this.employee_data.doj,"salary":this.employee_data.salary,"address":this.employee_data.address})
       
      }
        )
 }


ngOnInit(): void {

}

update(){
  this.service.registerEmployee(this.employeeUpdate.getRawValue()).subscribe(data=>
  {
    console.log(data)
  if(data!="failed")
  this.router.navigate(["employeesList"])})
  
}


}
