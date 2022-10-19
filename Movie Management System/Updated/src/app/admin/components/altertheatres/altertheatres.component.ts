import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TheatreInterface } from 'src/app/interface/theatre';
import { UserInterface } from 'src/app/interface/user';
import { TheatreDataService } from 'src/app/services/theatre-data.service';
import { UserdataService } from 'src/app/services/userdata.service';
import { TheatreFormComponent } from '../theatre-form/theatre-form.component';

@Component({
  selector: 'app-altertheatres',
  templateUrl: './altertheatres.component.html',
  styleUrls: ['./altertheatres.component.css']
})
export class AltertheatresComponent implements OnInit {
  public currentuser!:string

  public theatres!:TheatreInterface[]

  constructor(private theatre:TheatreDataService,private http:HttpClient,private dialog:MatDialog,private user:UserdataService) { }

  ngOnInit(): void {
    this.currentuser=this.user.getrole()
    this.theatre.getOwnertheatres(this.currentuser).subscribe(data=>this.theatres=data)
  }
  
  deleteMovie(id:number){
   
    console.log("work in progress")

  }
  addTheatre(){
    this.dialog.open(TheatreFormComponent)
  }

}
