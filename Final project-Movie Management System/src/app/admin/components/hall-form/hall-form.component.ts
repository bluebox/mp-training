import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { HallDataService } from 'src/app/services/hall-data.service';

@Component({
  selector: 'app-hall-form',
  templateUrl: './hall-form.component.html',
  styleUrls: ['./hall-form.component.css']
})
export class HallFormComponent implements OnInit {
  public hall:FormGroup=new FormGroup({
    Hall_no:new FormControl("",Validators.required),
    Date:new FormControl("",Validators.required),
    rows: new FormControl("",Validators.required),
    cols: new FormControl("",Validators.required),
    baseprice: new FormControl("",Validators.required),
    Theatre_id: new FormControl("",Validators.required)
  })

  constructor(private dialog:MatDialog,private halls:HallDataService) { }

  ngOnInit(): void {
  }
  OnSubmit(){

    this.halls.postHallData(this.hall.value).subscribe(data=>console.log(data))
    console.log("submitted")
    this.dialog.closeAll()
  }

}
