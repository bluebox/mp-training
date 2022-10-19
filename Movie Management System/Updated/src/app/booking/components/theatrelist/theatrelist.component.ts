import { Component, Inject, OnInit } from '@angular/core';
import { HallDataService } from 'src/app/services/hall-data.service';
import { TheatreDataService } from 'src/app/services/theatre-data.service';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { MoviedataService } from 'src/app/services/moviedata.service';
import { MovieInterface } from 'src/app/interface/movie';
import { HallInterface } from 'src/app/interface/hall';
import { TheatreInterface } from 'src/app/interface/theatre';
import { GetSeatNoComponent } from '../get-seat-no/get-seat-no.component';
import { DatePipe, Location } from '@angular/common';
import { FormControl, FormGroup } from '@angular/forms';
import { TheatrelistInterface } from 'src/app/interface/theatrelist';
import { ThisReceiver } from '@angular/compiler';
// import {MomentDateAdapter} from '@angular/material-moment-adapter';
// import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';

@Component({
  selector: 'app-theatrelist',
  templateUrl: './theatrelist.component.html',
  styleUrls: ['./theatrelist.component.css']
})
export class TheatrelistComponent implements OnInit {
  public  minDate!: Date;
  // public dd = String(this.minDate.getDate()).padStart(2, '0');
  // public mm = String(this.minDate.getMonth() + 1).padStart(2, '0'); //January is 0!
  // public yyyy =(this.minDate.getFullYear());
  public movies:any
  public theatres:TheatreInterface[]=[]
  public halls:HallInterface[]=[]
  public id:number=0
  public screen:HallInterface[]=[]
  public theatrename:TheatreInterface[]=[]
  public filteredData:any
  public moviehall:any
  public theatreall:any
  public seatsleft:number[]=[]
  public selectedDate!:Date
  public time:any
  public theatrelist:any
  getDate:FormGroup=new FormGroup({
    date:new FormControl("")
  })

  constructor(private hall:HallDataService,private theatre:TheatreDataService,private route:ActivatedRoute,private movie:MoviedataService,private dialog:MatDialog,private router:Router,private _location:Location,public datepipe: DatePipe) { 
    this.time =this.datepipe.transform((new Date),'HH:mm:ss');
  }

  ngOnInit(): void {
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
    console.log(this.selectedDate)
    this.hall.getTheatreList(this.id,this.getDate.value.date).subscribe(data=>{this.theatrelist=data;
      console.log(this.theatrelist)
      if(this.theatrelist.length==0){
        alert("sorry no theatres available")
      }}
      )
  }

 
}
