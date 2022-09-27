import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Student } from 'src/app/interface';
import { Customer } from 'src/app/interfaces/customer';
import { Employee } from 'src/app/interfaces/employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {


  public emp:Employee[]=[];

  constructor(private employee:EmployeeService,private router: Router) { }

  ngOnInit(): void {
  
      this.getData()
  }

  getData(){
    this.employee.getEmp().subscribe(data=>{console.log(data);this.emp=data})
    console.log(this.emp)
  }

  sigininForm=new FormGroup(
    {
      emp_id:new FormControl('', Validators.required),
      emp_name:new FormControl('', Validators.required),
      emp_username:new FormControl('', Validators.required),
      emp_password:new FormControl('', Validators.required),
      emp_phn:new FormControl('', Validators.required),
      emp_email:new FormControl('', Validators.required),
     
      // userPass2:new FormControl('', Validators.required)
    }
  )


  onSubmit()
  {
    if (this.sigininForm.valid) {
      this.employee.postEmp(this.sigininForm.value).subscribe((data)=>{
        console.log(data)
      })
      console.log('form submitted');
      console.log(this.sigininForm.value)
    } else {
      console.log(' notttt form submitted');
    }
    
    this.sigininForm.reset()

    this.router.navigate(['../employee/signin']);
  }

}
