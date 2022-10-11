import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

import { HeaderComponent } from 'src/app/components/header/header.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {

  constructor(private dataService: DataService,
    private route: Router
  ) { }
  activeBookings: any;
  // cancelBookingSubscription!: Subscription
  deleteSubscription!: Subscription
  subscription!: Subscription

  page: number = 1;
  pageSize: number = this.dataService.pageSize;
  length!: number;
  pageItems : any;
  totalPages : any;

  changePage(num: number){
    if(num>0){
      if(this.page < this.length/this.pageSize){
        this.page += num
        this.pageItems = this.activeBookings.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }else{
      if(this.page != 1 ){
        this.page += num
        this.pageItems = this.activeBookings.slice((this.page-1)*this.pageSize, this.page*this.pageSize)
      }
    }
  }



  ngOnInit(): void {
    this.subscription = this.dataService.getBookingList().subscribe(
      data => {
        console.log(data);
        this.activeBookings = data;
        this.length = this.activeBookings.length;
        this.totalPages = Math.ceil(this.length/this.pageSize);
        this.pageItems = this.activeBookings.slice(0, this.pageSize);
      },
      err => alert(err.error.detail)
    )
  }

  // cancelBooking(id: number){
  //   let message = 'There will be cancellation charges of 15% on every booking, Do you still want to cancel?';
  //   if(confirm(message)){
  //     this.cancelBookingSubscription = this.dataService.cancelBookingByAdmin(id).subscribe(
  //       data => {
  //         alert('Cancellation Successful')
  //         this.ngOnInit()
  //       },
  //       err=> alert(err.error.detail)
  //     )
  //   }
  // }


  editBooking(id: number) {
    this.route.navigate(['admin/bookings/editBooking', id])
  }

  deleteBooking(id: number){
    if(confirm("this object will be deleted, Do you want to continue")){
      this.deleteSubscription = this.dataService.deleteBooking(id).subscribe(
        data=>{
          alert(data +' deleted successfully');
          this.ngOnInit()
        },
      )
    }
  }

  ngOnDestroy(){
    if(this.deleteSubscription){
      this.deleteSubscription.unsubscribe()
    }
    if(this.subscription){
      this.subscription.unsubscribe()
    }
  }

}
