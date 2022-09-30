import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-employee-sign-up',
  templateUrl: './employee-sign-up.component.html',
  styleUrls: ['./employee-sign-up.component.css']
})
export class EmployeeSignUpComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private auth: AuthService) { }

  employeeForm!: FormGroup;
  roleTypes = ['Security', 'Helper', 'Supervisor', 'Admin'];
  roleId = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.email, Validators.required]);
  name = new FormControl('');
  address = new FormControl('');

  password = new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]);
  mobileNo = new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]);
  isAdmin = new FormControl(false, [Validators.required]);

  ngOnInit(): void {

    this.employeeForm = this.formBuilder.group({
      email: this.email,
      name: this.name,
      password: this.password,
      mobileNo: this.mobileNo,
      roleId: this.roleId,
      address: this.address,
      isAdmin: this.isAdmin
    }
    )

  }

  onSubmit(): void {

    try {
      this.auth.registerEmployee(this.employeeForm.value).subscribe(res => {
        console.log(res);
        // this.router.navigate(['/login']);
      }
      );
    }
    catch (error) {
      console.log(error);

    }


  }

}
