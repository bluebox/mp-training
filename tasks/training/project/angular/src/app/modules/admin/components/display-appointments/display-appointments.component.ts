import { Component, OnInit } from '@angular/core';
import { AdminSeriveService } from '../../admin-serive.service';

@Component({
  selector: 'app-display-appointments',
  templateUrl: './display-appointments.component.html',
  styleUrls: ['./display-appointments.component.css']
})
export class DisplayAppointmentsComponent implements OnInit {

  constructor(private http : AdminSeriveService) { }
  displayedColumns: string[] = ['appointment', 'user', 'branch', 'date', "slot", "doctor", "nurse", "lab_technician", "sample_collector", "status", "update",'delete'];
    appointments : any 

  ngOnInit(): void {
    this.http.getAppointments().subscribe({
      next:(resp)=>{
        this.appointments = resp
        console.log(this.appointments);
        
      },
      error:(err)=>{
        console.log(err.data);
      }
    })
  }

}
