import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HallDataService } from 'src/app/services/hall-data.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  public hall:FormGroup=new FormGroup({
    Hall_id:new FormControl("",Validators.required),
    Date:new FormControl("",Validators.required),
    T_No_Of_Seats: new FormControl("",Validators.required),
    startTime: new FormControl("",Validators.required),
    endTime: new FormControl("",Validators.required),
    Movie_id: new FormControl("",Validators.required)
  })

  constructor(private halls:HallDataService) { }

  ngOnInit(): void {
  }
  OnSubmit(){
    this.halls.postSchedule(this.hall.value).subscribe(data=>console.log("data posted"))
    console.log("working!")
  }

}
