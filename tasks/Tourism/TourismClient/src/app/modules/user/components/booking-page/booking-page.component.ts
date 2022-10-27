import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from 'rxjs';
import { DataService } from 'src/app/modules/admin/services/data.service';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-booking-page',
  templateUrl: './booking-page.component.html',
  styleUrls: ['./booking-page.component.css']
})
export class BookingPageComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private dataService: DataServiceService,
    private modalService: NgbModal,
    private router: Router
    ) { }

  booking: any;
  id!: number;
  cancellation_charges!: number;
  reason_for_cancellation!: string;
  passenger_details: any;
  routerSubscription!: Subscription;
  getBookingOfUserSubscription!: Subscription;
  cancelBookingSubscription!: Subscription;


  ngOnInit(): void {
    this.routerSubscription = this.route.params.subscribe(data=>{
      this.id = parseInt(data['id'])
      this.getBookingOfUserSubscription = this.dataService.getBookingOfUser(data['id']).subscribe(
        data => {
          console.log(data);
          this.booking = data;
          this.cancellation_charges = this.booking.tourid.price * .15
          this.passenger_details = JSON.parse(this.booking.passenger_details)
          console.log(this.passenger_details);
          // console.log("Charges", this.cancellation_charges);
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
    })
  }

  updateReason(e:any){
    this.reason_for_cancellation = e.target.value
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      // console.log(this.feedback, this.rating);
      this.cancelBooking()
    }, (reason) => {
      console.log(reason);
    });
  }

  cancelBooking(){
    let data = {
      cancellation_charges:this.cancellation_charges,
      reason_for_cancellation: this.reason_for_cancellation
    }
    this.cancelBookingSubscription = this.dataService.cancelBookingByUser(data, this.id).subscribe(
      data => {
        alert("Your booking is Cancelled")
        this.router.navigate(['user/bookings'])
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


  ngOnDestroy(){
    if(this.routerSubscription){
      this.routerSubscription.unsubscribe()
    }
    if(this.getBookingOfUserSubscription){
      this.getBookingOfUserSubscription.unsubscribe()
    }
    if(this.cancelBookingSubscription){
      this.cancelBookingSubscription.unsubscribe()
    }
  }

}
