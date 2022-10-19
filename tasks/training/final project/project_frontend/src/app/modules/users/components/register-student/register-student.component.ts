import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators,FormBuilder, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../http-service.service';


@Component({
  selector: 'app-register-student',
  templateUrl: './register-student.component.html',
  styleUrls: ['./register-student.component.css']
})
export class RegisterStudentComponent implements OnInit {


  formNotValid : boolean = false
  formError ?: string =""
  errormsg:string=""
  passwordmatch: boolean=true
  hide:boolean=true
  view:boolean=true
  p1:string[]=[]
  p2:string[]=[]
  i:number=0
  text:any

  constructor(private router:Router, private http: HttpServiceService) {  }


  studentRegisterForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl("", Validators.required),
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl("",[Validators.minLength(8),Validators.required]),
    passwordagain: new FormControl("",[Validators.minLength(8),Validators.required]),
    mobile_no: new FormControl("", [Validators.required]),
    address: new FormControl("", Validators.required),
    reqister_number: new FormControl("", Validators.required),
    college_name: new FormControl("", Validators.required)

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
    
    if (this.studentRegisterForm.get('password').value  === this.studentRegisterForm.get('passwordagain').value) {
      this.passwordmatch=true;
      console.log("rakesh")
    } 
  }
  submitRegister() {

    if (this.studentRegisterForm.valid ) {
      this.http.registerStudent(this.studentRegisterForm.value).subscribe(data => {

        this.errormsg=data.msg
        if(this.errormsg=="successful"){
          this.router.navigate(['userlogin'])
  
        }
        console.log(data)})
        
  
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      console.log(this.studentRegisterForm.valid);
    }
    console.log(this.studentRegisterForm.value);

  }
}




