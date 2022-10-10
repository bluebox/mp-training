import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ServercomunicationService } from '../servercomunication.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css'],
  providers:[ServercomunicationService]
})
export class AppointmentComponent implements OnInit {
  form_appointment: FormGroup = new FormGroup({});
  doctor: any;
  slot: any;
  constructor(private fb: FormBuilder,private api:ServercomunicationService) { }

  ngOnInit(): void {
    this.get_Doc_Data();
    this.get_Slot_Data();
    console.log(this.get_Doc_Data());
    console.log(this.get_Slot_Data());
  }
  get_Doc_Data()
{
  this.api.getAllDoctor().subscribe(
    (data)=>{
      console.log(data);
      this.doctor=data;
      console.log(this.doctor[0].d_id)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
get_Slot_Data()
{
  this.api.getAllSlot().subscribe(
    (data)=>{
      console.log(data);
      this.slot=data;
      console.log(this.slot[0].slot_id)
    },
    error=>{

  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}

}
