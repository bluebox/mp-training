import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {
  formstaff: FormGroup = new FormGroup({});
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formstaff = this.fb.group({
    name: [null, [Validators.required, Validators.minLength(10)]],
    email: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    dob: [null, [Validators.required]],
    phno: [null, [Validators.required, Validators.pattern("^[0-9]*$"),
      Validators.minLength(10)]],
    address: [null],
    country: [null],
    gender: [null],
    insuraceid: [null]
  });

}
saveDetails(formstaff: any) {
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(formstaff.value, null, 4));
}
}
