import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css'],
})
export class UserhomeComponent implements OnInit {
  uid: any;
  bookingDetails: any;
  sub1: any;
  constructor(private service: UserService, private arouter: ActivatedRoute) {}

  ngOnInit(): void {
    this.arouter.params.subscribe((data) => {
      this.uid = data['id'];
      console.log(data);
    });
    this.getuserBookings()
  


  }
  getuserBookings(){
    var date = new Date();
    date.setDate(date.getDate());
    const month = date.toLocaleString('default', { month: 'long' });
    const day = date.getDate();
    const year = date.getFullYear();
    const hour = date.getHours();
    const today = day + ',' + month + ',' + year;
    console.log(today);
    this.service.GetUserBookings(this.uid).subscribe((data:any) => {
      console.log(data)
      this.bookingDetails = data.map((obj: { date?: any; }) => {
        let Booking={}
        if (new Date(obj.date) >= new Date(today)){
          Booking = {...obj, isupcoming: true}
        }else{
          Booking = {...obj, isupcoming: false}
        }
        return Booking
      })
      console.log(this.bookingDetails);

    })
  }

  deletebooking(bid: any) {
    this.service.cancelUserBookings(bid).subscribe((data) => {
      console.log(data), this.getuserBookings();
    });
  }
  givefeedback(bid: any) {
    console.log(bid);
  }
}
