import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formlogIn: FormGroup = new FormGroup({});
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formlogIn = this.fb.group({
      email: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      usertype:[null],
      password: [null, [Validators.required, Validators.minLength(10)]]
  });
  }
saveDetails(formlogIn: any): void {
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(formlogIn.value, null, 3));
}
}
