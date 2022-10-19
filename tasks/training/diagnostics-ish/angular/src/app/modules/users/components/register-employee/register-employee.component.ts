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
  branches : any
  branchIdList : string[] =[]
  user_data : any
  invalidPassword: boolean = false
  constructor(private http: HttpServiceService, private router: Router) { }

  hide = true;
  employeeRegisterForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.required, Validators.email]),
    mobile_number: new FormControl("", [Validators.maxLength(12), Validators.required, Validators.pattern('[0-9]{10}')]),
    age: new FormControl(""),
    address: new FormControl(""),
    pincode: new FormControl("", [Validators.maxLength(6),  Validators.pattern('[0-9]{6}')]),
    qualification: new FormControl("", Validators.required),
    salary: new FormControl("", Validators.required),
    years_of_experience: new FormControl("", Validators.required),
    password: new FormControl("", [Validators.minLength(8), Validators.required]),
    passwordAgain: new FormControl("", [Validators.minLength(8), Validators.required]),

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
    this.employeeRegisterForm.get('username')?.setValue(this.employeeRegisterForm.get('username')?.value.trim())
    this.employeeRegisterForm.get('first_name')?.setValue(this.employeeRegisterForm.get('first_name')?.value.trim())
    this.employeeRegisterForm.get('last_name')?.setValue(this.employeeRegisterForm.get('last_name')?.value.trim())
    this.employeeRegisterForm.get('email')?.setValue(this.employeeRegisterForm.get('email')?.value.trim())
    this.employeeRegisterForm.get('address')?.setValue(this.employeeRegisterForm.get('address')?.value.trim())
    this.employeeRegisterForm.get('password')?.setValue(this.employeeRegisterForm.get('password')?.value.trim())
    this.employeeRegisterForm.get('passwordAgain')?.setValue(this.employeeRegisterForm.get('passwordAgain')?.value.trim())

    if (this.employeeRegisterForm.get('passwordAgain')?.value !== this.employeeRegisterForm.get('password')?.value) {
      this.invalidPassword = true
    }
    else {
      this.invalidPassword = false
      if (this.employeeRegisterForm.valid && this.designationControl.valid && this.branchControl.valid ) {
        this.formNotValid = false
        this.user_data = { ...this.employeeRegisterForm.value  ,"branch": this.branchControl.value, 'designation': this.designationControl.value}
        this.http.registerEmployee(this.user_data).subscribe(data => {
          console.log(data)
          this.errorMessage = data.message
          if (this.errorMessage == "registered") {
            this.router.navigate(['admin/dislplay-employees'])
          }
        })
      }
      else {
        this.formNotValid = true
        
      }
    }

  }
  DesignationChange(e: any) {
    console.log(e);

  }
}
