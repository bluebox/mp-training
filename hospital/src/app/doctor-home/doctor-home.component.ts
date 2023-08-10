import { Component, OnInit } from '@angular/core';
import { CheckboxControlValueAccessor } from '@angular/forms';
import { BookingService } from '../booking.service';
import { ServercomunicationService } from '../servercomunication.service';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import { Appointment } from '../appointment/appointment.component';
// ServercomunicationService

@Component({
  selector: 'app-doctor-home',
  templateUrl: './doctor-home.component.html',
  styleUrls: ['./doctor-home.component.css']
})
export class DoctorHomeComponent implements OnInit {
  displayedColumns: string[] = ['patient', 'slot', 'phone_no', 'apid','date','gender','inid','doctor'];
  dataSource!: MatTableDataSource<any>;
  user: any;
  userId: any;
  appointmentment: any;
  slot: any;


  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;
  constructor(private book: BookingService,private api:ServercomunicationService) {
    this.getAppointments();

   }

  ngOnInit(): void {

    // this.getUserDetails();


    // const mapped = Object.keys(this.appointmentment).map(key => ({type: key, value: this.appointmentment[key]}));
    // console.log(mapped)
    // this.checking();
  }
  getUserDetails(): any{




  }
  getAppointments():any{
    this.api.getAllAppointment().subscribe(
      (data)=>{
        console.log(data);
        this.appointmentment=data;
        // console.log(this.appointmentment)
        this.dataSource = new MatTableDataSource(this.appointmentment);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        console.log('datasource',this.paginator)
      },
      error=>{
    console.log(error);
  })
  }

  // checking():any{
  // for(this.i=0;this.i<8;this.i++)
  // this.getAppointments();
//  }
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    console.log(this.dataSource.paginator)
    this.dataSource.paginator.firstPage();
  }
}
}


