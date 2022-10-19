import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HallInterface } from 'src/app/interface/hall';
import { HallDataService } from 'src/app/services/hall-data.service';
import { UserdataService } from 'src/app/services/userdata.service';
import { HallFormComponent } from '../hall-form/hall-form.component';

@Component({
  selector: 'app-alter-halls',
  templateUrl: './alter-halls.component.html',
  styleUrls: ['./alter-halls.component.css']
})
export class AlterHallsComponent implements OnInit {
  public halls!:HallInterface[]
  public halldate!:any
  constructor(private hall:HallDataService,private dialog:MatDialog,private user:UserdataService) { }

  ngOnInit(): void {
    this.hall.getHallList(this.user.user.User_id).subscribe(data=>this.halls=data)
  }
  openHallForm(){
    this.dialog.open(HallFormComponent)
    this.dialog.closeAll()
  }
}
