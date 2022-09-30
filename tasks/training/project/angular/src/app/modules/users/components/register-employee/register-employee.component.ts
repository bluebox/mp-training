import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../../http-service.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

// interface 

@Component({
  selector: 'app-register-employee',
  templateUrl: './register-employee.component.html',
  styleUrls: ['./register-employee.component.css']
})
export class RegisterEmployeeComponent implements OnInit {
  formNotValid: boolean = false
  errorMessage: string = ""
  designations: string[] = ['Doctor', 'Nurse', 'Lab Technician', 'Sample Collector', 'Receptionist']
  status: string[] = ["occupied", "available"]
  branches : any
  branchIdList : string[] =[]
  user_data : any
  constructor(private http: HttpServiceService, private router: Router) { }


  employeeRegisterForm: FormGroup = new FormGroup({
    username: new FormControl(" ", Validators.required),
    first_name: new FormControl(" ", Validators.required),
    last_name: new FormControl(" ", Validators.required),
    email: new FormControl(" ", [Validators.required, Validators.email]),
    mobile_number: new FormControl(" ", [Validators.maxLength(12), Validators.required]),
    age: new FormControl(" ", Validators.required),
    address: new FormControl(" "),
    pincode: new FormControl(" ", Validators.maxLength(6)),
    qualification: new FormControl(" ", Validators.required),
    salary: new FormControl(" ", Validators.required),
    years_of_experience: new FormControl(" ", Validators.required),
    password: new FormControl(" ", Validators.minLength(8))
  })



  designationControl = new FormControl<string | null>(null, Validators.required);
  branchControl = new FormControl<string | null>(null, Validators.required);
  statusControl = new FormControl<string | null>(null, Validators.required);

  ngOnInit(): void {
    this.http.getBranches().subscribe(data => {
      this.branches = data
      console.log(this.branches);
    })

  }

  submitRegister() {
    // console.log(this.customerRegisterForm.value);
    if (this.employeeRegisterForm.valid && this.designationControl.valid && this.branchControl.valid ) {
      this.user_data = { ...this.employeeRegisterForm.value  ,"branch": this.branchControl.value, 'designation': this.designationControl.value}
      this.http.registerEmployee(this.user_data).subscribe(data => {
        console.log(data)
        this.errorMessage = data.message
        if (this.errorMessage == "registered") {
          this.router.navigate(['home'])
        }
      })
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      console.log(this.employeeRegisterForm.valid);
      console.log(this.designationControl.value);
    }
    console.log(this.employeeRegisterForm.value);

  }
  DesignationChange(e: any) {
    console.log(e);

  }
}
