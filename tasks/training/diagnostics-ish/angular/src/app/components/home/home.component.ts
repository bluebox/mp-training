import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentsService } from 'src/app/services/appointments-service/appointments.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user = ""
  constructor(
    private http: HttpClient  ,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

}
