import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HallDataService } from 'src/app/services/hall-data.service';
import { HallInterface } from 'src/app/interface/hall';
import { FormControl, FormGroup } from '@angular/forms';
import {Location} from '@angular/common';
import { ConfirmationComponent } from '../confirmation/confirmation.component';
import { BookingService } from 'src/app/services/booking.service';


@Component({
  selector: 'app-seats',
  templateUrl: './seats.component.html',
  styleUrls: ['./seats.component.css']
})
export class SeatsComponent implements OnInit {
  Number:FormGroup=new FormGroup({
    Seat: new FormControl("")
  })
  public pay:boolean=true
  public hallData!:HallInterface
  public hallid:number=0
  public clicked:number=0
  public test:string[]=[]
  public price:number=0
  public selectedSeats:any
  public ss:string[]=[]
  public update:any
  public sdate!:Date
  public currentHall:any
  public rows!:number[]
  public cols!:number[]
  public number!:number
  public chosen:number=0
  public selectedSeatsN:string[] = [];
  public dummyData:boolean=false
  public start:boolean=false
  public hidden:boolean=true
  constructor(private router:Router,private route:ActivatedRoute,private hall:HallDataService,private _location:Location,private dialog:MatDialog,private booking:BookingService) { 
   
  }
  
  ngOnInit(): void {
    this.sdate=this.hall.getDate()
    if(typeof(this.sdate)==='undefined'){
      this.router.navigate([''])
    }
    this.route.params.subscribe(params => {
      this.hallid=(params['hallid'])   
    })
  this.getHallData()
  this.blockSeats()
 

}


//function to get the hall data
getHallData(){
  this.hall.getSingleHall(this.hallid).subscribe((data)=>( this.hallData=data,this.rows=Array(this.hallData.rows),this.cols=Array(this.hallData.cols)))
}

blockSeats(){
  this.booking.getSelectedSeats(this.hallid).subscribe(data=>{this.selectedSeats=data,
    console.log(this.selectedSeats)
  for(let i=0;i<this.selectedSeats.length;i++){
    console.log("66",this.selectedSeats[i].Date,this.sdate)
    if(this.selectedSeats[i].Date==this.sdate){
      console.log(this.selectedSeats[i].Selected_seats)
      let temp=this.selectedSeats[i].Selected_seats.split(" ")
      for(let j=0;j<temp.length;j++){
        this.test.push(temp[j])
      }
    
  }}})
  console.log('previous',this.test)
  return this.test
}


selectseats(i:number,j:number)
{ 
 
  let seat=i.toString()+j.toString()
  if(this.selectedSeatsN.includes(i.toString()+j.toString())){
    let index=this.selectedSeatsN.indexOf(i.toString()+j.toString())
    this.selectedSeatsN.splice(index,1)
    console.log(this.selectedSeatsN)
    this.dummyData=true
    this.chosen--
  }
  else{
    console.log("frst else, can and are",this.number,this.chosen)
    if(this.chosen<this.number){
      console.log("chosen less the number",this.chosen,this.number)
      console.log("2nd if",this.number)
      this.selectedSeatsN.push(seat)
      console.log("seat has been pushed",this.selectedSeatsN)
      this.dummyData=false
      this.chosen++
      console.log("incremented choice is",this.chosen)
    }
    else if(typeof(this.number)!=='undefined')
    {
      console.log("2nd else",this.number,this.chosen)
      this.selectedSeatsN.splice(0,1,seat)
      console.log("2nd else after replacing",this.selectedSeatsN)
    }
  }
  if(this.chosen==this.number){
    this.pay=false
  }
  else{
    this.pay=true
  }
 
 
}

generateRows(){
  this.hidden=false
  let set=document.getElementById('seating')
  if(set!=null){
    set.style.display='block'
  }
  console.log(this.test)

  this.number=this.Number.value.Seat
}


//deduct the number of seats from database
DeductSeats(){
  this.pay=true
  console.log(this.test)
  this.price=this.selectedSeatsN.length*this.hallData.baseprice
  console.log(this.price)
  this.dialog.open(ConfirmationComponent,{data:{'selectedSeats':this.selectedSeatsN,'hallid':this.hallid,'price':this.price}})
}

goBack(){
  window.open("http://127.0.0.1:4200/movies")

}
}


