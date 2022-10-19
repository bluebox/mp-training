import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { HttpService } from 'src/app/services/http-service/http.service';

@Component({
  selector: 'app-register-customer',
  templateUrl: './register-customer.component.html',
  styleUrls: ['./register-customer.component.css']
})
export class RegisterCustomerComponent implements OnInit {
  hide = true;
  formNotValid: boolean = false
  formError?: string = ""
  errorMessage: string = ""
  invalidPassword: boolean = false
  // update
  customerId !: string
  customerDetails: any
  UserDetails: any
  appointments: any
  constructor(private http: HttpServiceService, private router: Router, private actRouter: ActivatedRoute, private customerHttp: HttpService) {
    this.actRouter.params.subscribe(data => {
      this.customerId = data['customer_id']
      console.log(this.customerId);
    })
    if (this.customerId) {
      this.customerHttp.getCustomer(this.customerId).subscribe({
        next: (data: any) => {
          this.customerRegisterForm.get('username')?.setValue(data["user_details"]['username'])
          this.customerRegisterForm.get('first_name')?.setValue(data["user_details"]['first_name'])
          this.customerRegisterForm.get('last_name')?.setValue(data["user_details"]['last_name'])
          this.customerRegisterForm.get('email')?.setValue(data["user_details"]['email'])
          this.customerRegisterForm.get('mobile_number')?.setValue(data["user_details"]['mobile_number'])
          this.customerRegisterForm.get('age')?.setValue(data["user_details"]['age'])
          this.customerRegisterForm.get('address')?.setValue(data["user_details"]['address'])
          this.customerRegisterForm.get('pincode')?.setValue(data["user_details"]['pincode'])
          this.customerRegisterForm.get('password')?.setValue(data["user_details"]['password'])
          this.customerRegisterForm.get('passwordAgain')?.setValue(data["user_details"]['password'])
        },
        error: (err: any) => {
          console.log(err);
        }
      })
    }
   }

  customerRegisterForm: FormGroup = new FormGroup({
    username: new FormControl("", Validators.required),
    first_name: new FormControl("", Validators.required),
    last_name: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.required, Validators.email]),
    mobile_number: new FormControl("", [Validators.maxLength(12), Validators.required, Validators.pattern('[0-9]{10}')]),
    age: new FormControl("", Validators.required),
    address: new FormControl("", Validators.required),
    pincode: new FormControl("", [Validators.maxLength(6), Validators.required, Validators.pattern('[0-9]{6}')]),
    password: new FormControl("", [Validators.minLength(8), Validators.required]),
    passwordAgain: new FormControl("", [Validators.minLength(8), Validators.required]),
    // password1: new FormControl(" ", Validators.minLength(8))
  })
  ngOnInit(): void {

  }
  submitRegister() {
    // console.log(this.customerRegisterForm.value);
    this.customerRegisterForm.get('username')?.setValue(this.customerRegisterForm.get('username')?.value.trim())
    this.customerRegisterForm.get('first_name')?.setValue(this.customerRegisterForm.get('first_name')?.value.trim())
    this.customerRegisterForm.get('last_name')?.setValue(this.customerRegisterForm.get('last_name')?.value.trim())
    this.customerRegisterForm.get('email')?.setValue(this.customerRegisterForm.get('email')?.value.trim())
    this.customerRegisterForm.get('address')?.setValue(this.customerRegisterForm.get('address')?.value.trim())
    this.customerRegisterForm.get('password')?.setValue(this.customerRegisterForm.get('password')?.value.trim())
    this.customerRegisterForm.get('passwordAgain')?.setValue(this.customerRegisterForm.get('passwordAgain')?.value.trim())

    if (this.customerRegisterForm.get('passwordAgain')?.value !== this.customerRegisterForm.get('password')?.value) {
      this.invalidPassword = true
    }
    else{
      this.invalidPassword = false
      if (this.customerRegisterForm.valid) {
        if(this.customerId){
          this.customerHttp.updateCustomer(this.customerId, this.customerRegisterForm.value).subscribe({
            next:(data:any)=>{
                this.errorMessage = data.message
                if (this.errorMessage == "updated") {
                  this.router.navigate([`/admin/display-customer/${this.customerId}`])
              }
            }
          })
        }
        else{
          this.http.registerCustomer(this.customerRegisterForm.value).subscribe(data => {
            this.errorMessage = data.message
            if (this.errorMessage == "registered") {
              this.router.navigate(['/login'])
            }
          })
        }
      }
      else {
        console.log('fill properly ');
        this.formNotValid = true
        console.log(this.customerRegisterForm.valid);
      }
    }



    

  }
}
