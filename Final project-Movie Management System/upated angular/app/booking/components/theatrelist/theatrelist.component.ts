import { Component, OnInit } from '@angular/core';
import { HallDataService } from 'src/app/services/hall-data.service';
import { TheatreDataService } from 'src/app/services/theatre-data.service';
import {MatDialog} from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { MovieInterface } from 'src/app/interface/movie';
import { DatePipe, Location } from '@angular/common';
import { FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-theatrelist',
  templateUrl: './theatrelist.component.html',
  styleUrls: ['./theatrelist.component.css']
})
export class TheatrelistComponent implements OnInit {
  public movies!:MovieInterface
  public id:number=0
  public selectedDate!:Date
  public time:any //to filter shows according to time
  public theatrelist!:any
  public sdate:any
  public text1:string=""
  public data:boolean=false
  getDate:FormGroup=new FormGroup({
    date:new FormControl("")
  })

  constructor(private hall:HallDataService,private theatre:TheatreDataService,private route:ActivatedRoute,private movie:MoviedataService,private dialog:MatDialog,private router:Router,private _location:Location,public datepipe: DatePipe) { 
    this.time =this.datepipe.transform((new Date),'HH:mm:ss');
    this.sdate=this.datepipe.transform((new Date),'yyyy-MM-dd')

  }

  ngOnInit(): void {
    console.log(this.sdate)
    console.log("IN THEATRELIST")
    console.log("time",this.time)
    this.route.params.subscribe(params => {
    this.id = params['id'];
    this.movie.getSingleMovie(Number(this.id)).subscribe(data=>this.movies=data)
   
});
    
  }
  OpenSeatNo(id:number){
    this.router.navigate(['booking/seating',id])
  }
  goback(){
    this._location.back()
  }
  check(){
    this.hall.setDate(this.getDate.value.date)
    this.selectedDate=this.hall.getDate()
    console.log(this.data)
    this.hall.getTheatreList(this.id,this.getDate.value.date).subscribe(data=>{this.theatrelist=data;
      console.log(this.theatrelist)
      this.data=true
      if(this.theatrelist.length==0){
        this.data=false
        // alert("sorry no theatres available")
      }}
      )
  }
  search(){
    if(this.text1===""){
      this.hall.getTheatreList(this.id,this.getDate.value.date).subscribe(data=>{this.theatrelist=data;
        console.log(this.theatrelist)
        this.data=true
        if(this.theatrelist.length==0){
          this.data=false
          // alert("sorry no theatres available")
        }}
        )
    }
    else{
    this.theatre.search(this.id,this.getDate.value.date,this.text1).subscribe(data=>{this.theatrelist=data,console.log(data);
       if(this.theatrelist.length==0){
        this.data=false}
      else{
        this.data=true
      }})}
  }
 
}
