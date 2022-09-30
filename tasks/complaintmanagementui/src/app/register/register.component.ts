import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  employeeform :FormGroup | undefined; 
  constructor(private service: ServicesService) {
    this.employeeform =  new FormGroup({
      emp_name : new FormControl(),
      emp_mobile : new FormControl(),
        emp_email : new FormControl(),
      emp_role : new FormControl(),
      facility_id : new FormControl(),
      emp_password: new FormControl(),

    })
   }
   registeremployee(){
    if(this.employeeform)
    {

    
    // console.log(this.employeeform.value);
    this.service.postEmployee(this.employeeform.value)
    }
   }

  ngOnInit(): void {
  }

}
