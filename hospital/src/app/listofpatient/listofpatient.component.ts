import { getLocaleDateFormat } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Patient } from '../interfaces/patients';
import { ServercomunicationService } from '../servercomunication.service';

@Component({
  selector: 'app-listofpatient',
  templateUrl: './listofpatient.component.html',
  styleUrls: ['./listofpatient.component.css'],
  providers:[ServercomunicationService]
})
export class ListofpatientComponent implements OnInit {
  displayedColumns: string[] = ['name', 'email', 'phone_no', 'id','inid','gender'];
  patients:any;
  dataSource: any;


  constructor(private api:ServercomunicationService) { }

  ngOnInit() {

    this.getData();

    console.log(this.getData());


  }


getData()
{
  this.api.getAllPatient().subscribe(
    (data)=>{
      console.log(data);
      this.patients=data;
      console.log(this.patients[0].name)
      this.dataSource = this.patients;
      console.log('datasource',this.dataSource)
    },
    error=>{
  console.log(error);

  // this.service.getAPatient().subscribe((data: Patient[])=>this.patients=data[0])
  // console.log(this.patients)
})
}
}
