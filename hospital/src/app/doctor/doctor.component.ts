import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
//import { NgMaterialModule } from '../ng-material/ng-material.module';
@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {
  formdoc: FormGroup = new FormGroup({});
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formdoc = this.fb.group({
      name: [null, [Validators.required, Validators.minLength(10)]],
      email: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      dob: [null, [Validators.required]],
      phno: [null, [Validators.required, Validators.pattern("^[0-9]*$"),
        Validators.minLength(10)]],
      address: [null],
      country: [null],
      gender: [null],
      salaryid: [null],
      yoex:[null, [Validators.required, Validators.pattern("^[0-9]*$"),
      Validators.minLength(1)]],
  });

}
saveDetails(formdoc: any) {
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(formdoc.value, null, 4));
}
}
