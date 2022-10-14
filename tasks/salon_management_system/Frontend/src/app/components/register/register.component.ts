import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpserviceService } from 'src/app/httpservice.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  formNotValid : boolean = false
  formError ?: string =""
  errorMessage : string = ""
  hide = true;
  

  constructor(private http :HttpserviceService ,private router: Router) { }

  ClientRegistrationForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl("",Validators.required),
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl("", [Validators.minLength(8),Validators.required]),
    Client_contact_number: new FormControl("", [Validators.maxLength(10), Validators.required]),

  })

  ngOnInit(): void {
  }
  onRegisterSubmit() {
    console.log(this.ClientRegistrationForm.value);
    if (this.ClientRegistrationForm.valid) {
      this.http.clientRegister(this.ClientRegistrationForm.value).subscribe(data =>{
        console.log(this.errorMessage = data.message);
        if(this.errorMessage == "success"){
          // alert("Registered succesfully press ok to login")
          
          this.router.navigate(['login']);
        }
        else{
          console.log(this.errorMessage = data.message);
        }
    })
    }
    else {
      console.log('please check ');
      this.formNotValid = true
      console.log(this.ClientRegistrationForm.valid);
       
    }
    console.log(this.ClientRegistrationForm.value);

  }
  get passwordInput() { return this.ClientRegistrationForm.get('password');} 

  keyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;

    const inputChar = String.fromCharCode(event.charCode);
    if (event.key != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
}


