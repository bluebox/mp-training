import { Component, OnInit } from '@angular/core';
import { ServercomunicationService } from '../servercomunication.service';
import {Doctor} from '../interfaces/doctor';
@Component({
  selector: 'app-doctor-details',
  templateUrl: './doctor-details.component.html',
  styleUrls: ['./doctor-details.component.css'],
  providers:[ServercomunicationService]
})
export class DoctorDetailsComponent implements OnInit {
  doctor: any;
  id:any;
  constructor(private api:ServercomunicationService ) {}

  ngOnInit(): void {
    this.getData();
    console.log(this.getData());
  }
  getData()
  {
    this.api.getADoctor(this.id).subscribe(
      (data)=>{
        console.log(data);
        this.doctor=data;
        console.log(this.doctor.d_id)
      },
      error=>{

    console.log(error);

    // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
    // console.log(this.patients)
  })
  }
}
