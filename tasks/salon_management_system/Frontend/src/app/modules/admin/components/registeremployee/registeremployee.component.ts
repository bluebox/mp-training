import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-registeremployee',
  templateUrl: './registeremployee.component.html',
  styleUrls: ['./registeremployee.component.css']
})
export class RegisteremployeeComponent implements OnInit {
  formNotValid : boolean = false
  formError ?: string =""
  errorMessage : string = ""
  hide = true;
  defaultvalue = "True";
  defaultid = 2500;

  constructor(private http :HttpserviceService ,private router: Router) { }



  employeeRegistrationForm : FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl(""),
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl("", [Validators.minLength(8),Validators.required]),
    emp_id :new FormControl(this.defaultid,Validators.required),
    branch_id : new FormControl(""),
    role : new FormControl("",Validators.required),
    emp_contact_number : new FormControl("",[Validators.maxLength(10), Validators.required]),
    is_staff : new FormControl(this.defaultvalue)

  })

  ngOnInit(): void {
  }
  onRegisterSubmit() {
    console.log(this.employeeRegistrationForm.value);
    console.log(this.employeeRegistrationForm.valid)
    if (this.employeeRegistrationForm.valid) {
      this.http.newEmployee(this.employeeRegistrationForm.value).subscribe(data =>{
        // this.errorMessage = data.message
        // console.log(this.errorMessage)
        console.log(this.defaultid)
        this.router.navigate(['admin/home'])
        this.defaultid=this.defaultid+1
        console.log(this.defaultid)
    })
    }
    else {
      console.log('please check ');
      this.formNotValid = true
      console.log(this.employeeRegistrationForm.valid);
       
    }
    console.log(this.employeeRegistrationForm.value);

  }
  get passwordInput() { return this.employeeRegistrationForm.get('password');} 
}
