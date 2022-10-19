import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-hall-form',
  templateUrl: './hall-form.component.html',
  styleUrls: ['./hall-form.component.css']
})
export class HallFormComponent implements OnInit {
  public hall:FormGroup=new FormGroup({
    Hall_no:new FormControl("",Validators.required),
    T_No_Of_Seats:new FormControl("",Validators.required),
    Date:new FormControl("",Validators.required),
    startTime:new FormControl(""),
    endTime:new FormControl(""),
    rows: new FormControl("",Validators.required),
    cols: new FormControl("",Validators.required),
    baseprice: new FormControl("",Validators.required),
    Theatre_id: new FormControl("",Validators.required),
    Movie_id: new FormControl("",Validators.required)
  })

  constructor() { }

  ngOnInit(): void {
  }
  OnSubmit(){
    console.log("submitted")
  }

}
