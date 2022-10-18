import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-updateappointment',
  templateUrl: './updateappointment.component.html',
  styleUrls: ['./updateappointment.component.css']
})
export class UpdateappointmentComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  id:any;
  constructor() { }

  ngOnInit(): void {
  }

}
