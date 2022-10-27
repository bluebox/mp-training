import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css'],
})
export class BookingsComponent implements OnInit {

  constructor(private dataService: DataServiceService,
    private modalService: NgbModal,
    private router: Router
    ) { }
  bookingsListSubscription!: Subscription
  postFeedbackSubscription!: Subscription
  // bookingList:any
  upcomingBookingList: any
  closedBookingList: any

  feedback = '';
  rating = 0;

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      console.log(this.feedback, this.rating);
      this.submitFeedback()
    }, (reason) => {
      console.log(reason);
    });
  }

  // openBooking(booking: any) {
  //   this.modalService.open(booking, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
  //     // console.log(this.feedback, this.rating);
  //     // this.submitFeedback()
  //   }, (reason) => {
  //     console.log(reason);
  //   });
  // }

  updateMessage(e:any){
    this.feedback=e.target.value
  }

  submitFeedback(){
    this.postFeedbackSubscription = this.dataService.postFeedbackApi({comment:this.feedback, rating:this.rating}).subscribe(
      data=>{
        alert("Feedback submitted successfully")
      },
      err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
    )
  }

  ngOnInit(): void {
    this.bookingsListSubscription = this.dataService.getBookingListOfUser().subscribe(
      data=>{
        let dataString = JSON.stringify(data)
        let dataList = JSON.parse(dataString)
        this.upcomingBookingList = dataList.filter((booking: any) => new Date(booking.tourid?.start_date) > new Date() && !booking.isCancelled)
        this.closedBookingList = dataList.filter((booking: any) => new Date(booking.tourid?.start_date) <= new Date() || booking.isCancelled)
      },
      err => {
          if(err.status == 404){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
    )

  }

  navigateToBooking(id: number){
    this.router.navigate(['user/bookings', id])
  }

  // dateString(date="2022-10-28T21:30:00Z"){
  //   new Date(date).toISOString()
  // }

  ngOnDestroy(){
    if(this.bookingsListSubscription){
      this.bookingsListSubscription.unsubscribe()
    }
    if(this.postFeedbackSubscription){
      this.postFeedbackSubscription.unsubscribe()
    }
  }


}
