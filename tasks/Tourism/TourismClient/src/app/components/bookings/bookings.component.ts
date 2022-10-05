import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css'],
})
export class BookingsComponent implements OnInit {

  constructor(private dataService: DataServiceService
    ) { }
  subscription!: Subscription
  bookingList:any



  ngOnInit(): void {
    this.subscription = this.dataService.getBookingListOfUser().subscribe(data=>{
      console.log(data);
      this.bookingList = data
    })

  }

  ngOnDestroy(){
    if(this.subscription){
      this.subscription.unsubscribe()
    }
  }


}
