import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-bookingdetails',
  templateUrl: './bookingdetails.component.html',
  styleUrls: ['./bookingdetails.component.css']
})
export class BookingdetailsComponent implements OnInit {
  bid:any;
  booking: any;
  bookingDetails:any;
  invoice:any;
  bookedEquip:any;
  constructor(private arouter: ActivatedRoute,private service:UserService) { }

  ngOnInit(): void {
    this.arouter.params.subscribe((data) => {
      this.bid = data['id'];
      console.log(data);
      this.getBookingDetails()
      this.getBookedEquipDetails()
    
    
    
    });
  }
  getBookingDetails(){
    this.service.getBookingDetails(this.bid).subscribe(data => {
      console.log(data);
      this.booking = data;
      this.bookingDetails = this.booking.booking
      this.invoice = this.booking.invoice
      console.log(this.bookingDetails.equipments_booked)
      console.log(this.invoice)
    })
    
  }
  getBookedEquipDetails(){
    console.log("232")
    this.service.getBookedEquipDetails(this.bid).subscribe(data => {
      console.log("hello")
      this.bookedEquip=data;
      console.log(this.bookedEquip)
    })
  }
  // print(){
  //   window.print()
  // }
}
