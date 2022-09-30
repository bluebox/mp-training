import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
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

  constructor(@Inject(MAT_DIALOG_DATA) public data: {selectedSeats: string[],hallid:number,price:number},private hall:HallDataService,private theatre:TheatreDataService,private movie:MoviedataService,private router:Router,private dialog:MatDialog) { }
  id=this.data.hallid
  ngOnInit(): void {
 
    this.hall.getSingleHall(this.id).subscribe(data=>{this.hallData=data,this.getTheatre(this.hallData.Theatre_id),this.getMovie(this.hallData.Movie_id)})
    
    
  }
  getTheatre( id:number){
    this.theatre.getSingleTheatre(id).subscribe(data=>this.theareData=data)
  }
  getMovie(id:number){
    this.movie.getSingleMovie(id).subscribe(data=>this.movieData=data)
  }
  gotoPayment(){
    this.dialog.open(PaymentComponent,{data:{'price':this.data.price}})
  }

}
