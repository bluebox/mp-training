  import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatAccordion } from '@angular/material/expansion';

import { AppointmentsService } from 'src/app/services/appointments-service/appointments.service';
import { CloseDialogComponent } from '../../close-dialog/close-dialog.component';
import { Router } from '@angular/router';

export interface AppointmentData {
  appointment_id: string;
  customer_id: string;
  username: string;
  date: string;
  slot: string;
  tests: string;
  // doctor_id: string;
  doctor: string;
  // nurse_id: string;
  nurse: string;
  // lab_technician_id: string;
  lab_technician: string;
  // sample_collector_id: string;
  sample_collector: string;
  status: string;
}

@Component({
  selector: 'app-display-appointments',
  templateUrl: './display-appointments.component.html',
  styleUrls: ['./display-appointments.component.css'],
})
export class DisplayAppointmentsComponent implements AfterViewInit, OnInit {
  appointments: any;
  searchedAppointments: any;
  tests: any;
  searchedTests: any;
  dataSource: MatTableDataSource<AppointmentData>;
  searchText !: string


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatAccordion) accordion!: MatAccordion;

  constructor(private appointments_service: AppointmentsService, public dialog: MatDialog, private router: Router) {
    this.dataSource = new MatTableDataSource(this.appointments);
  }

  displayedColumns: string[] = [
    'appointment id',
    'customer id',
    'customer name',
    'date',
    'slot',
    // 'doctor id',
    'doctor',
    // 'nurse id',
    'nurse',
    // 'lab technician id',
    'lab technician',
    // 'sample collector id',
    'sample collector',
    'status',
    'tests',
    'delete',
    'update',
  ];

  getAppointments() {
    this.appointments_service.getAppointments().subscribe({
      next: (data: any) => {
        this.appointments = data.appointments;
        console.log(data);
        
        this.tests = data.related_tests;
        this.tests = JSON.parse(this.tests);
        // this.appointments = JSON.parse(this.appointments);
        this.dataSource.data = this.appointments;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  ngOnInit(): void {
    this.getAppointments()
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  openDialog(id: any) {
    let dialogRef = this.dialog.open(CloseDialogComponent)

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result == 'true') {
        this.appointments_service.deleteAppointment(id).subscribe({
          next: (res) => {
            console.log(res);
            this.getAppointments()
          }
        })
      }
    })
  }

  updateAppointment(id: any) {
    this.router.navigate(['admin/edit-appointment', id])
  }


  // search bar

  onSearchTextEntered(searchValue: string) {
    if (searchValue.length>0) {
      this.searchText = searchValue
      this.appointments_service.getSearchedAppointments(this.searchText).subscribe({
        next: (data: any) => {
          this.searchedAppointments = data.appointments;
          this.searchedTests = data.related_tests;
          this.searchedTests = JSON.parse(this.searchedTests);
          this.dataSource.data = this.searchedAppointments;
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
    else if (searchValue.length ==0){
      this.dataSource.data = this.appointments;
    }
  }
}
