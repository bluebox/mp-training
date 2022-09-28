import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpServiceService } from '../../http-service.service';

@Component({
  selector: 'app-register-customer',
  templateUrl: './register-customer.component.html',
  styleUrls: ['./register-customer.component.css']
})
export class RegisterCustomerComponent implements OnInit {
  formNotValid : boolean = false
  formError ?: string =""
  constructor(private http: HttpServiceService) { }

  customerRegisterForm: FormGroup = new FormGroup({
    username : new FormControl(" ", Validators.required),
    first_name: new FormControl(" ", Validators.required),
    last_name: new FormControl(" ", Validators.required),
    email: new FormControl(" ", [Validators.required, Validators.email]),
    mobile_number: new FormControl(" ", [Validators.maxLength(12), Validators.required]),
    age: new FormControl(" ", Validators.required),
    address: new FormControl(" "),
    pincode: new FormControl(" ", Validators.maxLength(6)),
    password: new FormControl(" ", Validators.minLength(8))
  })
  ngOnInit(): void {
  }
  submitRegister() {
    // console.log(this.customerRegisterForm.value);
    if (this.customerRegisterForm.valid) {
      this.http.registerCustomer(this.customerRegisterForm.value).subscribe(data => console.log(data))
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      console.log(this.customerRegisterForm.valid);
       
    }
    console.log(this.customerRegisterForm.value);

  }
}
