import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  constructor() { }

  reviewForm : FormGroup = new FormGroup({
    Appointment_id : new FormControl("",Validators.required),
    comments_and_reviews :new FormControl("",Validators.required),
    rating :new FormControl("",Validators.required)
  })
  ngOnInit(): void {
  }
  onSubmit(){
    
  }
}
