import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { ServercomunicationService } from '../servercomunication.service';
import { Router } from '@angular/router';

export interface UserFormInterface {
  name:string
  email:string
  password:string
  type_of_user:string

}
@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css'],
  providers:[ServercomunicationService],
})
// interface Patient{
//   id:string;
//   name:string;
//   mail:string;
//   gende:string;

// }
export class PatientComponent implements OnInit {
  patientForm: FormGroup = new FormGroup({});
  patients: any;
  userform:UserFormInterface={
    name: '',
    email: '',
    password: '',
    type_of_user: ''
  };
  constructor(private fb: FormBuilder,private api:ServercomunicationService,private router:Router) { }

  ngOnInit(): void {
    this.patientForm = this.fb.group({
    name: [null, [Validators.required]],
    email: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    age: [null, [Validators.required]],
    phone_no: [null, [Validators.required, Validators.pattern("^[0-9]*$"),Validators.minLength(10)]],
    address: [null],
    gender: [null,[Validators.required]],
    // id: [null, [Validators.required]],
    insurance_id:[null],
    password:[null,[Validators.required]],
  });

}
saveDetails(){
  // const data=JSON.stringify(this.form.value)
  console.log(this.patientForm.value.name)

  this.userform!.name=this.patientForm.value.name
  this.userform!.email=this.patientForm.value.email
  this.userform!.password=this.patientForm.value.password
  this.userform!.type_of_user="P"
  console.log(this.userform)

  this.api.registerUser(this.userform).subscribe(
    (data)=>{
      console.log(data);
      alert('SUCCESS!! :-)\n\n');
      this.router.navigate(['/login/'])
    },
    error=>{

  console.log(error);
})
  this.api.register_patient(this.patientForm.value).subscribe(
    (data)=>{
      console.log(data);
    },
    error=>{

  console.log(error);

})
}
}


