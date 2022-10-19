import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../http-service.service';

@Component({
  selector: 'app-register-teacher',
  templateUrl: './register-teacher.component.html',
  styleUrls: ['./register-teacher.component.css']
})
export class RegisterTeacherComponent implements OnInit {

  formNotValid : boolean = false
  formError ?: string =""
  errormsg:string=''
  hide:boolean=true
  view:boolean=true
  text:any
  passwordmatch: boolean=true

  constructor(private router:Router, private http: HttpServiceService) { }

  teacherRegisterForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl("", Validators.required),
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl("", [Validators.minLength(8),Validators.required]),
    passwordagain: new FormControl("",[Validators.minLength(8),Validators.required]),
    mobile_no: new FormControl("", [Validators.required]),
    address: new FormControl("", Validators.required),
    qualification: new FormControl("", Validators.required),
    position: new FormControl("", Validators.required)

  })

  ngOnInit(): void {
  }
  keyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;

    const inputChar = String.fromCharCode(event.charCode);
    if (event.key != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  onChange(event:any) {
    this.text = event.target.value;
    this.passwordmatch=false;
    
    if (this.teacherRegisterForm.get('password').value  === this.teacherRegisterForm.get('passwordagain').value) {
      this.passwordmatch=true;
      console.log("rakesh")
    } 
  }

  submitRegister() {
    console.log(this.teacherRegisterForm.value);
    if (this.teacherRegisterForm.valid) {
      this.http.registerTeacher(this.teacherRegisterForm.value).subscribe(data =>{
        
        this.errormsg=data.msg
        if(data.msg=="successful"){
          this.router.navigate(['userlogin'])
        }
        console.log(data)})
      
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      console.log(this.teacherRegisterForm.valid);
    }
    console.log(this.teacherRegisterForm.value);

  }

}
