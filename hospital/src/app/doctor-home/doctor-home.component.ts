import { Component, OnInit } from '@angular/core';
import { BookingService } from '../booking.service';

@Component({
  selector: 'app-doctor-home',
  templateUrl: './doctor-home.component.html',
  styleUrls: ['./doctor-home.component.css']
})
export class DoctorHomeComponent implements OnInit {
  user: any;
  userId: any;

  constructor(private book: BookingService) { }

  ngOnInit(): void {
    this.getUserDetails();
  }
  getUserDetails(): any{
    this.user=this.book.getUserDetails()
    console.log(this.user);
    this.userId=this.user.id;
    console.log(this.userId);
    

  }
}
