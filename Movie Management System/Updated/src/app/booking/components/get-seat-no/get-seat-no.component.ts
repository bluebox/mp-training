import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { SeatsComponent } from '../seats/seats.component';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';


@Component({
  selector: 'app-get-seat-no',
  templateUrl: './get-seat-no.component.html',
  styleUrls: ['./get-seat-no.component.css']
})
export class GetSeatNoComponent implements OnInit {
  Seats:FormGroup=new FormGroup({
    NumberSeats: new FormControl("")
  })

  constructor(private router:Router,private route:ActivatedRoute,private dialog:MatDialog,@Inject(MAT_DIALOG_DATA) public data: {id: number}) { }

  ngOnInit(): void {
  }
  goToSeats(){
    // console.log(this.Seats.value)
    
    let id=this.Seats.value
    console.log(id.NumberSeats)
    this.router.navigate(['booking/seating',Number(id.NumberSeats),this.data.id])
    // this.dialog.open(SeatsComponent,{data:{'id':id.NumberSeats}})

  }

}
