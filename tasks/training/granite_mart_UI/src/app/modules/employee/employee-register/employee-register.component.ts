import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-employee-register',
  templateUrl: './employee-register.component.html',
  styleUrls: ['./employee-register.component.css']
})

export class EmployeeRegisterComponent implements OnInit {

 
  employeeReg = new FormGroup({
    employee_id: new FormControl('',Validators.required),
    employee_name: new FormControl('',Validators.required),
    phone: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required),
    doj: new FormControl('',Validators.required),
    role: new FormControl('',Validators.required),
    salary: new FormControl('',Validators.required),
    address: new FormControl('',Validators.required)

  });


roles:any
employee_data:any=''
constructor(public service:DataServiceService,private router:Router) { 
      this.service.getRoles().subscribe(data=>
        {this.roles=data})
 }

ngOnInit(): void {
}

register(){
  this.service.registerEmployee(this.employeeReg.getRawValue()).subscribe(data=>
  {
    console.log(data)
  if(data!="failed")
  this.router.navigate(["employeesList"])})
  
}
}
