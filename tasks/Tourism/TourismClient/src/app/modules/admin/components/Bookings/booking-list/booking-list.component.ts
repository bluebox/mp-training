import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

import { HeaderComponent } from 'src/app/components/header/header.component';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';

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
  pageItems : any;
  totalPages : any

  searchText: FormGroup = new FormGroup({
    text: new FormControl(''),
  });

  getPageItems(num: number, searchText = this.searchText.get('text')?.value){
    this.subscription = this.dataService.getBookingList(this.page + num, searchText).subscribe(
      (data: any) => {
        console.log(data);
        this.pageItems = data.pageItems
        this.page = data.page
        this.totalPages = data.totalPages
      },
      err => {
        if(err.status == 404){
          // alert('Requested Url' + err.url + "Not Found")
          alert(err.message)
        }
        else{
          alert(err.error.detail)
        }
      }
    )
  }

  ngOnInit(): void {
    this.getPageItems(0)
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
        err => {
          if(err.status == 404){
            // alert('Requested Url' + err.url + "Not Found")
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
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
