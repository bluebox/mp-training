import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY

  formNotValid : boolean = false
  formError ?: string =""
  errorMessage : string = ""
  constructor(private http :HttpserviceService ,private router: Router) { }

  reviewForm : FormGroup = new FormGroup({
    Appointment_id : new FormControl("",Validators.required),
    comments_and_reviews :new FormControl("",Validators.required),
    rating :new FormControl("",Validators.required)
  })
  ngOnInit(): void {
  }
  onSubmit(){
    console.log(this.reviewForm.value)
    if(this.reviewForm.value){
      this.http.addreview(this.reviewForm.value).subscribe(data => {
        console.log(data);
        this.errorMessage=data.msg;
        if(this.errorMessage == "successful"){
          alert("review added successfully");
          this.router.navigate(['client/allreviews'])
        }
      })
    }
 }
}