import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-empolyeeregisterpage',
  templateUrl: './empolyeeregisterpage.component.html',
  styleUrls: ['./empolyeeregisterpage.component.css']
})
export class EmpolyeeregisterpageComponent implements OnInit {
  data:any;
  users:any;
  error:any;
  constructor(private userdata:UserserviceService){
   
  }

  ngOnInit(): void {
  }
  empLoginForm = new FormGroup({
    employee_name:new FormControl(''),
    emp_email: new FormControl('', [Validators.required, Validators.email]),
    emp_password: new FormControl('', [Validators.required, Validators.minLength(5)]),
    employee_address:new FormControl('')

  })

  get employee_name(){
    return this.empLoginForm.get("employee_name")

  }

  get emp_email(){
    return this.empLoginForm.get("emp_email")

  }
  get emp_password(){
    return this.empLoginForm.get('emp_password')
  }
  get employee_address(){
    return this.empLoginForm.get('employee_address')
  }

  registerSubmit(){
     
    console.log(this.empLoginForm.value);
    this.userdata.empapi(this.empLoginForm.value).subscribe((res)=>{
      console.log(res);
      // console.log(this.empLoginForm.value);
      let strin = JSON.stringify(res)
      let parse = JSON.parse(strin)
      // console.log(parse.length);
          if (parse.token){
            this.error = "Registration Successful"
            setTimeout(function(){
              window.location.reload();
          }, 5000)

            
          }
          else{

         
          this.error = "This Email is already existed"
            setTimeout(function(){
              window.location.reload();
          }, 5000)
          }

        
      
      
       
    
    })
  }
}
