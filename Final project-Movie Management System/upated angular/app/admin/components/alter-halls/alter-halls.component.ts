import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HallInterface } from 'src/app/interface/hall';
import { HallDataService } from 'src/app/services/hall-data.service';
import { UserdataService } from 'src/app/services/userdata.service';
import { HallFormComponent } from '../hall-form/hall-form.component';
import {MatTableDataSource} from '@angular/material/table';
import { ScheduleComponent } from '../schedule/schedule.component';
@Component({
  selector: 'app-alter-halls',
  templateUrl: './alter-halls.component.html',
  styleUrls: ['./alter-halls.component.css']
})
export class AlterHallsComponent implements OnInit {
  displayedColumns: string[] = ['Hall_id', 'Hall_no', 'Rows', 'cols'];
  public dataSource:any
  public halls:any
  public halldate!:any
  constructor(private hall:HallDataService,private dialog:MatDialog,private user:UserdataService, private http:HttpClient) { }

  ngOnInit(): void {
    this.hall.getHallList(this.user.user.User_id).subscribe(data=>{this.halls=data, this.dataSource = new MatTableDataSource(this.halls);})
  }
  openHallForm(){
    this.dialog.open(HallFormComponent)
  }
  deleteHall(id:number){
    this.http.delete("http://127.0.0.1:8000/movie/"+id+".json").subscribe(
      res => {
        this.hall.getHallList(this.user.user.User_id).subscribe(data=>this.halls=data)
      }
    );
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.halls.filter = filterValue.trim().toLowerCase();
  }
  openSchedule(){
    this.dialog.open(ScheduleComponent)
  }
}
