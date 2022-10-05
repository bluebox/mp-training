import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators,FormBuilder } from '@angular/forms';
import { HttpServiceService } from '../../http-service.service';


@Component({
  selector: 'app-register-student',
  templateUrl: './register-student.component.html',
  styleUrls: ['./register-student.component.css']
})
export class RegisterStudentComponent implements OnInit {


  formNotValid : boolean = false
  formError ?: string =""

  constructor(private http: HttpServiceService) {  }


  studentRegisterForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl("", Validators.required),
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl("",[Validators.minLength(8),Validators.required]),
    mobile_no: new FormControl("", [Validators.maxLength(12), Validators.required]),
    address: new FormControl(""),
    reqister_number: new FormControl(""),
    college_name: new FormControl("", Validators.required)

  })



  ngOnInit(): void {
  }

  submitRegister() {
    // console.log(this.studentRegisterForm.value);
    if (this.studentRegisterForm.valid) {
      this.http.registerStudent(this.studentRegisterForm.value).subscribe(data => console.log(data))
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      console.log(this.studentRegisterForm.valid);
    }
    console.log(this.studentRegisterForm.value);

  }
}




