import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataService } from '../../../services/data.service';

@Component({
  selector: 'app-edit-booking',
  templateUrl: './edit-booking.component.html',
  styleUrls: ['./edit-booking.component.css']
})
export class EditBookingComponent implements OnInit {

  routeSubscription!: Subscription;
  getBookingSubscription!: Subscription;
  editBookingSubscription!: Subscription;
  addBookingSubscription!: Subscription;
  getUserSubscription!: Subscription;
  getPaymentSubscription!: Subscription;
  getTourSubscription!: Subscription;
  userList: any;
  paymentList: any;
  tourList: any;

  constructor(private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
    ) {
    this.routeSubscription = this.route.params.subscribe(res => {
      if(parseInt(res['id'])){
        this.id = parseInt(res['id'])
        this.getBookingSubscription = this.dataservice.getBooking(parseInt(res['id'])).subscribe(
          data=> {
          let bookingString = JSON.stringify(data)
          let bookingObj = JSON.parse(bookingString)
          this.BookingForm.get('userid')?.setValue(bookingObj.userid);
          this.BookingForm.get('tourid')?.setValue(bookingObj.tourid);
          this.BookingForm.get('paymentid')?.setValue(bookingObj.paymentid);
          this.BookingForm.get('no_of_people')?.setValue(bookingObj.no_of_people);
          this.BookingForm.get('passenger_details')?.setValue(bookingObj.passenger_details);
          this.BookingForm.get('isCancelled')?.setValue(bookingObj.isCancelled);
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
      );
      }
    })
  }
  id!: number

  BookingForm: FormGroup = new FormGroup({
    userid : new FormControl('', [Validators.required]),
    tourid : new FormControl('', [Validators.required, Validators.email]),
    paymentid : new FormControl('', [Validators.required]),
    no_of_people : new FormControl('', [Validators.required]),
    passenger_details : new FormControl('', [Validators.required]),
    isCancelled : new FormControl(''),
  })

  get formObj(){
    return this.BookingForm.controls
  }


  ngOnInit(): void {
    this.getUserSubscription = this.dataservice.getAllUserList().subscribe(
      data=> {
        this.userList = data;
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
    this.getPaymentSubscription = this.dataservice.getPaymentList().subscribe(
      data=> {
        this.paymentList = data;
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
    this.getTourSubscription = this.dataservice.getAllToursList().subscribe(
      data=> {
        this.tourList = data;
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

  addBookingObj() {
    if(this.id){
      if(this.BookingForm.valid){
        this.editBookingSubscription = this.dataservice.editBooking(this.BookingForm.value, this.id).subscribe(
          data=>{
            console.log(data)
            alert("Booking updated Successfully")
            this.router.navigate(['admin/bookings/bookingList'])
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
    }else{
      if(this.BookingForm.valid){
        this.addBookingSubscription = this.dataservice.addBooking(this.BookingForm.value).subscribe(
          data=>{
            console.log(data)
            alert("Booking added Successfully")
            this.router.navigate(['admin/bookings/bookingList'])
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
  }

  ngOnDestroy(){
    if(this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
    if(this.getBookingSubscription){
      this.getBookingSubscription.unsubscribe()
    }
    if(this.editBookingSubscription){
      this.editBookingSubscription.unsubscribe()
    }
    if(this.getUserSubscription){
      this.getUserSubscription.unsubscribe()
    }
    if(this.getPaymentSubscription){
      this.getPaymentSubscription.unsubscribe()
    }
    if(this.getTourSubscription){
      this.getTourSubscription.unsubscribe()
    }
  }

}
