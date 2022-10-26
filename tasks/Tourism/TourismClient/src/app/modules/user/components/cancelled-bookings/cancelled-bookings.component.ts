import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-cancelled-bookings',
  templateUrl: './cancelled-bookings.component.html',
  styleUrls: ['./cancelled-bookings.component.css']
})
export class CancelledBookingsComponent implements OnInit {

  constructor(private dataService: DataServiceService,
    private router: Router
    ) { }

  bookingsListSubscription!: Subscription;
  bookings : any;

  ngOnInit(): void {
    this.bookingsListSubscription = this.dataService.getBookingListOfUser().subscribe(
      data=>{
        let dataString = JSON.stringify(data)
        let dataList = JSON.parse(dataString)
        // this.bookings = dataList
        this.bookings = dataList.filter((booking: any) => booking.isCancelled)
        console.log(this.bookings);
        // this.closedBookingList = dataList.filter((booking: any) => new Date(booking.tourid?.start_date) <= new Date() || booking.isCancelled)
      },
      err => alert(err.error.detail)
    )
  }

  navigateToBooking(id: number){
    this.router.navigate(['user/bookings', id])
  }

  ngOnDestroy(){
    if(this.bookingsListSubscription){
      this.bookingsListSubscription.unsubscribe()
    }
  }

}
