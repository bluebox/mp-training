import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ServercomunicationService } from '../servercomunication.service';
//import { NgMaterialModule } from '../ng-material/ng-material.module';
export interface UserFormInterface {
  name:string
  email:string
  password:string
  type_of_user:string

}
@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css'],
  providers:[ServercomunicationService],
})

export class DoctorComponent implements OnInit {
  formdoc: FormGroup = new FormGroup({});
  doctor: any;
  userform:UserFormInterface={
    name: '',
    email: '',
    password: '',
    type_of_user: ''
  };
  constructor(private fb: FormBuilder,private api:ServercomunicationService) { }

  ngOnInit(): void {
    this.formdoc = this.fb.group({
      name: [null, [Validators.required, Validators.minLength(10)]],
      email: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      age: [null, [Validators.required]],
      phone_no: [null, [Validators.required, Validators.pattern("^[0-9]*$"),
        Validators.minLength(10)]],
      address: [null],
      // country: [null],
      gender: [null],
      qualification: [null],
      experience:[null, [Validators.required, Validators.pattern("^[0-9]*$"),
      Validators.minLength(1)]],
      password:[null,[Validators.required]],
  });

}
// saveDetails(formdoc: any) {
//   alert('SUCCESS!! :-)\n\n' + JSON.stringify(formdoc.value, null, 4));
// }
saveDetails(form:any){
  console.log(this.formdoc.value.name)

  this.userform!.name=this.formdoc.value.name
  this.userform!.email=this.formdoc.value.email
  this.userform!.password=this.formdoc.value.password
  this.userform!.type_of_user="D"
  console.log(this.userform)

  this.api.registerUser(this.userform).subscribe(
    (data)=>{
      console.log(data);
    },
    error=>{

  console.log(error);
})
  const data=JSON.stringify(form.value)
  this.api.register_doctor(data).subscribe(


    (data)=>{
      console.log(data);
      this.doctor=data;
      // console.log(this.doctor)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
}
