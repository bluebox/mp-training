import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { ServercomunicationService } from '../servercomunication.service';
@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css'],
  providers:[ServercomunicationService]
})
// interface Patient{
//   id:string;
//   name:string;
//   mail:string;
//   gende:string;

// }
export class PatientComponent implements OnInit {
  form: FormGroup = new FormGroup({});
  patients: any;
  constructor(private fb: FormBuilder,private api:ServercomunicationService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
    p_name: [null, [Validators.required, Validators.minLength(10)]],
    p_mail: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    // p_dob: [null, [Validators.required]],
    p_phone_no: [null, [Validators.required, Validators.pattern("^[0-9]*$"),
      Validators.minLength(10)]],
    p_address: [null],
    p_gender: [null],
    p_id: [null, [Validators.required]],
    p_insurance_id:[null],
  });

}
saveDetails(form:any){
  const data=JSON.stringify(form.value)
  this.api.register_patient(data).subscribe(
    (data)=>{
      console.log(data);
      this.patients=data;
      console.log(this.patients.p_id)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
}


