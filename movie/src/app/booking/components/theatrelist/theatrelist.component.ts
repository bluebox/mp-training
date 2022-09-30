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
import { Location } from '@angular/common';


@Component({
  selector: 'app-theatrelist',
  templateUrl: './theatrelist.component.html',
  styleUrls: ['./theatrelist.component.css']
})
export class TheatrelistComponent implements OnInit {
  public movies:any
  public theatres:TheatreInterface[]=[]
  public halls:HallInterface[]=[]
  public id:number=0
  public screen:HallInterface[]=[]
  public theatrename:TheatreInterface[]=[]
  constructor(private hall:HallDataService,private theatre:TheatreDataService,private route:ActivatedRoute,private movie:MoviedataService,private dialog:MatDialog,private router:Router,private _location:Location) { }

  ngOnInit(): void {
    this.theatre.getTheatres().subscribe(data=>this.theatres=data)
    this.hall.getHallData().subscribe(data=>this.halls=data)
    this.route.params.subscribe(params => {
    this.id = params['id'];
    this.movie.getSingleMovie(Number(this.id)).subscribe(data=>this.movies=data)
   
});
    
  }
  getlist() {
    console.log(this.halls)
    for(let i=0;i<this.halls.length;i++){
      console.log("inside the loop")
      console.log(this.halls[i].Movie_id,Number(this.id))
      if (Number(this.id)==this.halls[i].Movie_id) 
      {
        this.screen.push(this.halls[i]);
        console.log(this.screen)
        for(let j=0;j<this.theatres.length;j++){
          // console.log(this.theatres[j].Theatre_id,this.halls[i])
          if(this.halls[i].Theatre_id==this.theatres[j].Theatre_id){
            this.theatrename.push(this.theatres[j])
            console.log("working!!",this.screen)
          }
          else{
            console.log("skip")
          }
        }
      }
      else{
        console.log("no theatres")
      }
    } 
    return this.screen,this.theatrename
  }

  // OpenSeatNo(id:number){
  //   this.dialog.open(GetSeatNoComponent,{data:{'id':id}})
  // }
  OpenSeatNo(id:number){
    // console.log(this.Seats.value)
    
    // let id=this.Seats.value
    // console.log(id.NumberSeats)
    this.router.navigate(['booking/seating',id])
    // this.dialog.open(SeatsComponent,{data:{'id':id.NumberSeats}})

  }
  goback(){
    this._location.back()
  }


}
