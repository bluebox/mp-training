import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  appointment : any;
  formNotValid : boolean = false
  formError ?: string =""
  errorMessage : string = ""
  constructor(private http :HttpserviceService ,private router: Router,private route:ActivatedRoute) { }

  reviewForm : FormGroup = new FormGroup({
    Appointment_id : new FormControl("",Validators.required),
    comments_and_reviews :new FormControl("",Validators.required),
    rating :new FormControl("",[Validators.required,Validators.max(5)])
  })
  ngOnInit(): void {
  }
  onSubmit(){
    console.log(this.reviewForm.value)
    if(this.reviewForm.valid){
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