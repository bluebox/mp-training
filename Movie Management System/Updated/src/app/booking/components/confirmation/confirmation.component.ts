import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BookingInterface } from 'src/app/interface/booking';
import { HallInterface } from 'src/app/interface/hall';
import { MovieInterface } from 'src/app/interface/movie';
import { TheatreInterface } from 'src/app/interface/theatre';
import { BookingService } from 'src/app/services/booking.service';
import { HallDataService } from 'src/app/services/hall-data.service';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { TheatreDataService } from 'src/app/services/theatre-data.service';
import { PaymentComponent } from '../payment/payment.component';
import { TheatrelistComponent } from '../theatrelist/theatrelist.component';
BookingService

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {
  public hallData!:HallInterface
  public theareData!:TheatreInterface
  public movieData!:MovieInterface
  // public booking!:BookingInterface
  public ss:string=''
  public sdate!:Date
  public datewiseHall:any

  constructor(@Inject(MAT_DIALOG_DATA) public data: {selectedSeats: string[],hallid:number,price:number},private hall:HallDataService,private theatre:TheatreDataService,private movie:MoviedataService,private router:Router,private dialog:MatDialog) { }
  id=this.data.hallid
  ngOnInit(): void {
    this.getSelectedSeats(this.data.selectedSeats)
    this.hall.getSingleHall(this.id).subscribe(data=>{this.hallData=data,
      this.getTheatre(this.hallData.Theatre_id),
      this.getMovie(this.hallData.Movie_id)
      })
    this.sdate=this.hall.getDate()
    console.log("c",this.sdate)
    this.hall.SingleHall(this.id,this.sdate).subscribe(data=>this.datewiseHall=data)
    
    
  }
  getTheatre( id:number){
    this.theatre.getSingleTheatre(id).subscribe(data=>this.theareData=data)
  }
  getMovie(id:number){
    this.movie.getSingleMovie(id).subscribe(data=>this.movieData=data)
  }
  getSelectedSeats(seats:any){
    for(let i=0;i<this.data.selectedSeats.length;i++){
      this.ss=this.ss+" "+this.data.selectedSeats[i]
      console.log(this.data.selectedSeats[i],this.ss)
    }
    return this.ss
  }
  gotoPayment(){
    // this.booking.SelectedSeats=this.ss
    // console.log(this.booking)
    // this.booking.Hall_id=this.data.hallid
    let booking={
      'User_id': 1,
      'Movie_id': this.hallData.Movie_id,
      'Theatre_id':this.hallData.Theatre_id,
      'Hall_id': this.data.hallid,
      'Date':this.datewiseHall.Date,
      'Selected_seats':this.ss
    }
    console.log(booking)
    this.dialog.open(PaymentComponent,{data:{'price':this.data.price,'bookingData':booking}})
  }

}
