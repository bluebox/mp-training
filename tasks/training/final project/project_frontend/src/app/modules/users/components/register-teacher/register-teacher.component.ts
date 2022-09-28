import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpServiceService } from '../../http-service.service';

@Component({
  selector: 'app-register-teacher',
  templateUrl: './register-teacher.component.html',
  styleUrls: ['./register-teacher.component.css']
})
export class RegisterTeacherComponent implements OnInit {

  constructor(private http: HttpServiceService) { }

  teacherRegisterForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl("", Validators.required),
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl("", Validators.minLength(8)),
    mobile_no: new FormControl("", [Validators.maxLength(12), Validators.required]),
    address: new FormControl(""),
    qualification: new FormControl(""),
    position: new FormControl("")

  })

  ngOnInit(): void {
  }

  submitRegister() {
    console.log(this.teacherRegisterForm.value);
    if (this.teacherRegisterForm.valid) {
      this.http.registerTeacher(this.teacherRegisterForm.value).subscribe(data => console.log(data))
    }
    else {
      console.log('fill properly ');
    }
    console.log(this.teacherRegisterForm.value);

  }

}
