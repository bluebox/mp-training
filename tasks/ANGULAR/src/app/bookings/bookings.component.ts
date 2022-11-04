import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {

mylist:any;
myid:any;
  constructor(private service:SharedService) { }

  ngOnInit(): void {
    this.myid=localStorage.getItem("userid")
    console.log(this.myid);

   this.refresh_bookings()
    // console.log(this.mylist);

  }




refresh_bookings()
{
  var item={"passenger_id":this.myid}
  this.service.getbookings(item).subscribe((data)=>
  {

    let res = JSON.stringify(data)
    let resObj = JSON.parse(res)
    this.mylist=resObj
    console.log(this.mylist);

  })

}



}
